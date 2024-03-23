package com.example.app.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.app.model.City

@Entity(tableName = "cities")
data class CityEntity(
    @PrimaryKey
    val id: Int,
    val name: String,
    val description: String,
    val location: String,
    val image: String,
    @ColumnInfo(name = "image_credit")
    val imageCredit: String,
    val website: String,
    @ColumnInfo(name = "best_time")
    val bestTime: String,
    @ColumnInfo(name = "best_time_reason")
    val bestTimeReason: String,
    val events: String,
    @ColumnInfo(name = "more_info")
    val moreInfo: String,
    @ColumnInfo(name = "info_credit")
    val infoCredit: String
)

fun CityEntity.toExternalModel() = City(
    id = id,
    name = name,
    description = description,
    location = location,
    image = image,
    imageCredit = imageCredit,
    website = website,
    bestTime = bestTime,
    bestTimeReason = bestTimeReason,
    events = events,
    moreInfo = moreInfo,
    infoCredit = infoCredit
)