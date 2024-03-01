package com.example.app.feature.placedetail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.app.data.PlaceData
import com.example.app.data.PlaceRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

data class PlaceDetailUIState(
    val place: PlaceData,
    // TODO: Implement this later
    val isFavorited: Boolean = false
)

class PlaceDetailViewModel(savedStateHandle: SavedStateHandle) : ViewModel() {
    private val _uiState = MutableStateFlow(
        PlaceDetailUIState(
        PlaceRepository.get(checkNotNull(savedStateHandle[PLACE_ID])),
        false)
    )
    val uiState = _uiState.asStateFlow()
}
