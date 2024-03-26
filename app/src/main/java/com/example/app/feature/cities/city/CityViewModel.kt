package com.example.app.feature.cities.city

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.app.data.CitiesRepository
import com.example.app.data.PlaceFilterQuery
import com.example.app.data.PlacesRepository
import com.example.app.data.UserDataRepository
import com.example.app.model.City
import com.example.app.model.Place
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CityViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    placesRepository: PlacesRepository,
    citiesRepository: CitiesRepository,
    private val userDataRepository: UserDataRepository
) : ViewModel() {

    private val cityArgs = CityArgs(savedStateHandle)
    private val cityId = cityArgs.cityId

    val uiState = cityUIState(
        cityId = cityId,
        citiesRepository = citiesRepository,
        placesRepository = placesRepository,
        userDataRepository = userDataRepository
    ).stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = CityUIState.Loading
    )

    fun setSavedCity(saved: Boolean) {
        viewModelScope.launch {
            userDataRepository.setSavedCity(cityId, saved)
        }
    }
}

private fun cityUIState(
    cityId: String,
    citiesRepository: CitiesRepository,
    placesRepository: PlacesRepository,
    userDataRepository: UserDataRepository
): Flow<CityUIState> {
    val city = citiesRepository.getCity(cityId)
    val places = placesRepository.getPlaces(
        filterQuery = PlaceFilterQuery(
            city = cityId
        )
    )
    val userData = userDataRepository.userData.map { it.savedCities }

    return combine(city, places, userData, ::Triple)
        .map {
            CityUIState.Success(
                city = it.first,
                places = it.second,
                isSaved = cityId in it.third
            )
        }
        .onStart { CityUIState.Loading }
        .catch { CityUIState.Error }
}

sealed interface CityUIState {
    data class Success(
        val city: City,
        val places: List<Place>,
        val isSaved: Boolean,
    ) : CityUIState

    data object Error : CityUIState
    data object Loading : CityUIState
}
