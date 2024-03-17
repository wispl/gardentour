package com.example.app.feature.placedetail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.app.data.PlaceFilterQuery
import com.example.app.data.PlacesRepository
import com.example.app.data.model.Place
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

data class PlaceDetailUIState(
    val place: List<Place>,
    // TODO: Implement this later
    val isFavorited: Boolean = false
)

@HiltViewModel
class PlaceDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    placesRepository: PlacesRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(
        PlaceDetailUIState(
        placesRepository.getPlaces(
            PlaceFilterQuery(
                name = checkNotNull(savedStateHandle[PLACE_ID]),
            )
        ),
        false)
    )
    val uiState = _uiState.asStateFlow()
}
