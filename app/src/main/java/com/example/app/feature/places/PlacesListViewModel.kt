package com.example.app.feature.places

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.app.data.PlaceFilterQuery
import com.example.app.data.PlacesRepository
import com.example.app.data.model.Place
import com.example.app.data.model.PlaceType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class PlacesListViewModel @Inject constructor(
    private val placesRepository: PlacesRepository
): ViewModel() {
    private val _activeFilters = MutableStateFlow(setOf<PlaceType>())
    val activeFilters = _activeFilters.asStateFlow()

    @OptIn(ExperimentalCoroutinesApi::class)
    val places: StateFlow<List<Place>> = activeFilters
        .flatMapLatest {
            placesRepository.getPlaces(PlaceFilterQuery(types = activeFilters.value))
        }
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