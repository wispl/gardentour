package com.example.app.feature.places.place

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.app.data.PlacesRepository
import com.example.app.data.UserDataRepository
import com.example.app.model.Place
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltViewModel
class PlaceDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    placesRepository: PlacesRepository,
    private val userDataRepository: UserDataRepository
) : ViewModel() {

    private val placeArgs = PlaceArgs(savedStateHandle)
    private val placeId = placeArgs.placeId
    private val test = MutableStateFlow(true)

    val uiState = placeUIState(
        placeId = placeArgs.placeId,
        placesRepository = placesRepository,
        userDataRepository = userDataRepository
    ).stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = PlaceUIState.Loading
    )

    fun setSavedPlace(saved: Boolean) {
        viewModelScope.launch {
            userDataRepository.setSavedPlace(placeId, saved)
        }
    }
}

private fun placeUIState(
    placeId: String,
    placesRepository: PlacesRepository,
    userDataRepository: UserDataRepository
): Flow<PlaceUIState> {
    val savedPlaces = userDataRepository.userData.map { it.savedPlaces }
    val place = placesRepository.getPlace(placeId)

    return combine(savedPlaces, place, ::Pair)
        .map { PlaceUIState.Success(it.second, placeId in it.first) }
        .onStart { PlaceUIState.Loading }
        .catch { PlaceUIState.Error }
}

sealed interface PlaceUIState {
    data class Success(val place: Place, val isSaved: Boolean) : PlaceUIState
    data object Error : PlaceUIState
    data object Loading : PlaceUIState
}