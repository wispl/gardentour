package com.example.app.feature.places.place

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.app.data.PlacesRepository
import com.example.app.model.Place
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

// TODO: use DataStore for storing followed places
@HiltViewModel
class PlaceDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    placesRepository: PlacesRepository
) : ViewModel() {

    private val placeArgs = PlaceArgs(savedStateHandle)
    private val placeId = placeArgs.placeId
    val isFavorited = MutableStateFlow(false)

    val uiState = placeUIState(
        placeId,
        isFavorited,
        placesRepository
    ).stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = PlaceUIState.Loading
    )
}

private fun placeUIState(
    placeId: String,
    isFavorited: Flow<Boolean>,
    placesRepository: PlacesRepository
) : Flow<PlaceUIState> {
    val place = placesRepository.getPlace(placeId)

    return combine(place, isFavorited, ::Pair)
        .map { PlaceUIState.Success(it.first, it.second) }
        .onStart { PlaceUIState.Loading }
        .catch { PlaceUIState.Error }
}

sealed interface PlaceUIState {
    data class Success(val place: Place, val isFavorited: Boolean) : PlaceUIState
    data object Error : PlaceUIState
    data object Loading : PlaceUIState
}