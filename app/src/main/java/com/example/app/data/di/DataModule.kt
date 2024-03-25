package com.example.app.data.di

import com.example.app.data.*
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {
    @Binds
    abstract fun bindsPlacesRepository(placesRepository: OfflinePlacesRepository): PlacesRepository

    @Binds
    abstract fun bindsSearchRepository(searchRepository: DefaultSearchRepository): SearchRepository

    @Binds
    abstract fun bindsCityRepository(cityRepository: OfflineCitiesRepository): CitiesRepository
}
