package com.example.app.feature.cities.city

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.app.data.CitiesRepository
import com.example.app.data.PlacesRepository
import com.example.app.model.City
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class CityViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    placesRepository: PlacesRepository,
    citiesRepository: CitiesRepository
) : ViewModel() {

    private val cityArgs = CityArgs(savedStateHandle)
    private val cityId = cityArgs.cityId
    val isFavorited = MutableStateFlow(false)

    val uiState = cityUIState(
        cityId,
        isFavorited,
        citiesRepository,
        placesRepository
    ).stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = CityUIState.Loading
    )
}

private fun cityUIState(
    cityId: String,
    isFavorited: Flow<Boolean>,
    citiesRepository: CitiesRepository,
    placesRepository: PlacesRepository
) : Flow<CityUIState> {
    val city = citiesRepository.getCity(cityId)

    return combine(city, isFavorited, ::Pair)
        .map { CityUIState.Success(it.first, it.second) }
        .onStart { CityUIState.Loading }
        .catch { CityUIState.Error }
}

sealed interface CityUIState {
    data class Success(
        val city: City,
        val isFavorited: Boolean,
    ) : CityUIState
    data object Error : CityUIState
    data object Loading : CityUIState
}
