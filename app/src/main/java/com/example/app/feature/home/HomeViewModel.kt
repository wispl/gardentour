package com.example.app.feature.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.app.data.PlacesRepository
import com.example.app.model.Place
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    placesRepository: PlacesRepository
) : ViewModel() {
    val uiState = placesRepository
        .getRandomPlace()
        .map { HomeUIState.Success(it)  }
        .onStart { HomeUIState.Loading }
        .catch { HomeUIState.Error }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = HomeUIState.Loading
        )
}

sealed interface HomeUIState {
    data class Success(val place: Place) : HomeUIState
    data object Error : HomeUIState
    data object Loading: HomeUIState
}