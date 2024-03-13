package com.example.app.feature.cities

import androidx.lifecycle.ViewModel
import com.example.app.data.CitiesRepository
import com.example.app.data.model.City
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class CitiesViewModel @Inject constructor(
    citiesRepository: CitiesRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(CitiesUIState(citiesRepository.allCities()))
    val uiState = _uiState.asStateFlow()
}

data class CitiesUIState (
    val cities: List<City>
)