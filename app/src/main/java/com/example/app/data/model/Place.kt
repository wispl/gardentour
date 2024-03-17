package com.example.app.data.model

import androidx.annotation.DrawableRes
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle

enum class PlaceType {
    Fun,
    Museum,
    Shopping,
    Food,
    Landmark,
    Zoo
}

data class Image(
    @DrawableRes
    val image: Int,
    val desc: String
)

data class Address(
    val number: Int,
    val address: String,
    val city: String
) {
    override fun toString(): String = this.number.toString() + " " + this.address + ", " + this.city
}

sealed interface Hours {
    // Uses 24 hour time
    data class Range(val start: LocalTime, val end: LocalTime) : Hours {
        init {
            require(start < end)
        }

        override fun toString() : String {
            val format = DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT)
            return start.format(format) + " to " + end.format(format)
        }
    }

    data object Vary : Hours {
        override fun toString(): String = "Hours varies (i.e. by seasons)"
    }

    data object AlwaysOpen : Hours {
        override fun toString(): String = "Always open"
    }
}

data class Place(
    val name: String,
    val description: String,
    @DrawableRes
    val image: Int,
    val address: Address,
    val types: Set<PlaceType>,
    val url: String = "",
    val time: Hours = Hours.AlwaysOpen,
    val price: String = "Free"
)