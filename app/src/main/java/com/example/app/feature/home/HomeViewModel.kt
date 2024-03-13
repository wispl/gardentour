package com.example.app.feature.home

import androidx.lifecycle.ViewModel
import com.example.app.data.PlacesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    placesRepository: PlacesRepository
) : ViewModel() {
    val place = MutableStateFlow(placesRepository.getRandomPlace())
}