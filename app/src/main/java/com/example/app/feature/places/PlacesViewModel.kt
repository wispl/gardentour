package com.example.app.feature.places

import androidx.lifecycle.ViewModel
import com.example.app.data.PlaceRepository
import com.example.app.data.PlaceType
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

data class PlacesUIState(val activeFilters: Set<PlaceType> = setOf()) {
    val places = PlaceRepository.filterByType(activeFilters)
}

class PlacesViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(PlacesUIState())
    val uiState = _uiState.asStateFlow()

    fun allFilters(): List<PlaceType> {
        return PlaceType.entries.toList()
    }

    fun toggleFilter(placeType: PlaceType) {
        _uiState.update { state ->
            PlacesUIState(
                activeFilters = if (placeType in state.activeFilters) {
                    state.activeFilters - placeType
                } else {
                    state.activeFilters + placeType
                },
            )
        }
    }

    fun clearFilters() {
        _uiState.update { PlacesUIState(activeFilters = setOf()) }
    }
}