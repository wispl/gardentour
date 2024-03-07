package com.example.app.data

object SearchRepository {
    // Soundex key mapped to names of places it matched
    // Each word of the place's name is encoded and merged together
    private val soundexMap = PlaceRepository.allPlaces()
        .map { place -> place.name.split(" ")
            .map { soundexEncodeWord(it) to place.name }
        }
        .flatten()
        .groupBy({ it.first }, {it.second})

    fun search(str: String): List<PlaceData> {
        assert(str.isNotEmpty())
        return soundexEncode(str).map { soundexMap.getOrDefault(it, emptyList()) }
            .flatten()
            .map { PlaceRepository.get(it) }
    }

    private fun soundexEncode(str: String): List<String> {
        assert(str.isNotEmpty())
        return str.split(" ").map { soundexEncodeWord(it) }
    }

    private fun soundexEncodeWord(str : String): String {
        assert(str.isNotEmpty())

        var result: String = str.substring(0, 1)
        var prev = str[0]
        for (i in str.drop(1)) {
            if (soundexShouldSkip(i)) {
                prev = i
                continue
            }

            val encoded = soundexEncodeChar(i).toChar()
            when (prev) {
                'a', 'e', 'i', 'o', 'u' -> result += encoded
                'w', 'h', 'y' -> if (encoded != result.last()) result += encoded
                else -> if (encoded != result.last()) result += encoded
            }
        }

        if (result.length < 4) {
            result.padEnd(4, '0')
        } else if (result.length > 4) {
            result = result.substring(0, 5)
        }

        return result
    }

    private fun soundexEncodeChar(c: Char): Int {
        return when (c) {
            'b', 'f', 'p', 'v' -> 1
            'c', 'g', 'j', 'k', 'q', 's', 'x', 'z' -> 2
            'd', 't' -> 3
            'l' -> 4
            'm', 'n' -> 5
            'r' -> 6
            else -> throw IllegalStateException("Invalid Char in Soundex Encode")
        }
    }

    private fun soundexShouldSkip(c: Char) = c in setOf('a', 'e', 'i', 'o', 'u', 'y', 'h', 'w')
}
