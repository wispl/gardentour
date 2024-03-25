package com.example.app.database

import com.example.app.database.dao.CityDao
import com.example.app.database.dao.PlaceDao
import com.example.app.database.dao.PlaceFtsDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DaosModule {
    @Provides
    fun providesPlaceDao(database: AppDatabase): PlaceDao = database.placeDao()

    @Provides
    fun providesPlaceFtsDao(database: AppDatabase): PlaceFtsDao = database.placeFtsDao()

    @Provides
    fun providesCityDao(database: AppDatabase): CityDao = database.cityDao()
}