package com.example.app.data

object SearchRepository {
    // Soundex key mapped to names of places it matched
    // Each word of the place's name is encoded and merged together
    private val placesSoundexMap = PlaceRepository.allPlaces()
        .map { place -> place.name.split(" ")
            .map { soundexEncodeWord(it) to place.name }
        }
        .flatten()
        .groupBy({ it.first }, {it.second})

    fun search(str: String): List<PlaceData> {
        if (str.isEmpty()) {
            return emptyList()
        }
        return soundexEncode(str)
            .map { placesSoundexMap.getOrDefault(it, emptyList()) }
            .flatten()
            .map { PlaceRepository.get(it) }
    }

    private fun soundexEncode(str: String): List<String> {
        assert(str.isNotEmpty())
        return str.split(" ").map { soundexEncodeWord(it) }
    }

    private fun soundexEncodeWord(word: String): String {
        assert(word.isNotEmpty())

        val str = word.lowercase()
        var result = ""
        var prev: Char? = null

        for (i in str) {
            var mapping = soundexMap(i)
            if (result.isEmpty()) {
                result += str.first().uppercase()
            } else if (mapping == '0') {
                mapping = null
            } else if (mapping != null && mapping != prev) {
                result += mapping
            } else {
                mapping = prev
            }
            prev = mapping
        }

        return result.padEnd(4, '0').substring(0, 4)
    }

    private fun soundexMap(c: Char): Char? {
        return when (c) {
            'a', 'e', 'i', 'o', 'u' -> '0'
            'b', 'f', 'p', 'v' -> '1'
            'c', 'g', 'j', 'k', 'q', 's', 'x', 'z' -> '2'
            'd', 't' -> '3'
            'l' -> '4'
            'm', 'n' -> '5'
            'r' -> '6'
            // w, h, y are handled here as well
            else -> null
        }
    }
}
