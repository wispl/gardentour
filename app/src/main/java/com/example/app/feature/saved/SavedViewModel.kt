package com.example.app.feature.saved

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.app.data.CitiesRepository
import com.example.app.data.PlacesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class SavedViewModel @Inject constructor(
    placesRepository: PlacesRepository,
    citiesRepository: CitiesRepository,
) : ViewModel() {

    val savedPlaces = placesRepository
        .getSavedPlaces()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = emptyList()
        )

    val savedCities = citiesRepository
        .getSavedCities()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = emptyList()
        )
}