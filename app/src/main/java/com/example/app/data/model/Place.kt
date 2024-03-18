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
    Zoo,
    Hotel
}

data class Image(
    @DrawableRes
    val image: Int,
    val desc: String
)

sealed interface Hours {
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
        override fun toString(): String = "Hours varies by seasons"
    }

    data object AlwaysOpen : Hours {
        override fun toString(): String = "Always open"
    }
}

sealed interface Price {
    data object FreeEntry : Price {
        override fun toString(): String = "Free entry"
    }

    data object Vary : Price {
        override fun toString(): String = "Price varies (such as by seasons or selections)"
    }

    data class Cost(val children: IntRange, val adult: IntRange) : Price {
        constructor(price: Int) : this(price, price)
        constructor(children: Int, adult: Int) :
                this(children..children, adult..adult)
        constructor(children: IntRange, adult: Int) :
                this(children, adult..adult)
        constructor(children: Int, adult: IntRange) :
                this(children..children, adult)
    }
}

data class Place(
    val name: String,
    val description: String,
    @DrawableRes
    val image: Int,
    val address: String,
    val city: String,
    val types: Set<PlaceType>,
    val url: String = "",
    val time: Hours = Hours.AlwaysOpen,
    val price: Price = Price.FreeEntry,
    val id: Int = 0,
)