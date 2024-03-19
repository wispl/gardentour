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

// Helper constructors for constructing strings for Pricing
sealed interface Price {
    data object FreeEntry : Price {
        override fun toString(): String = "Free Entry"
    }
    data object Vary : Price {
        override fun toString(): String = "Price varies by seasons or selection"
    }
    data class Cost(val cost: String) : Price {
        constructor(price: Int) : this("$$price")
        constructor(adult: Int, children: Int) : this("Adults: $$adult     Children: $$children")
        constructor(daily: Int, weekly: Int, seasonally: Int) :
                this("Daily: $$daily     Weekly: $$weekly     Seasonally: $$seasonally")
        override fun toString() = cost
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
    val price: String = Price.FreeEntry.toString(),
    val id: Int = 0,
)