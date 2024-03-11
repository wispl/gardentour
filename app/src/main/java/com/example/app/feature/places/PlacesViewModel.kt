package com.example.app.feature.places

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.app.data.Place
import com.example.app.data.PlaceRepository
import com.example.app.data.PlaceType
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*

class PlacesViewModel : ViewModel() {
    private val _activeFilters = MutableStateFlow(setOf<PlaceType>())
    val activeFilters = _activeFilters.asStateFlow()

    @OptIn(ExperimentalCoroutinesApi::class)
    val places: StateFlow<List<Place>> = activeFilters
        .flatMapLatest { flowOf(PlaceRepository.filterByType(it)) }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = emptyList()
        )

    fun allFilters(): Set<PlaceType> {
        return PlaceType.entries.toSet()
    }

    fun toggleFilter(placeType: PlaceType) {
        _activeFilters.update {
            if (placeType in it) {
                it - placeType
            } else {
                it + placeType
            }
        }
    }
}