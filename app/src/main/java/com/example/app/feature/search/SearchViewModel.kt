package com.example.app.feature.search

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.app.data.PlaceData

import com.example.app.data.SearchRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*

private const val SEARCH_QUERY: String = "searchQuery"
class SearchViewModel(private val savedStateHandle: SavedStateHandle) : ViewModel() {
    val searchQuery = savedStateHandle.getStateFlow(key = SEARCH_QUERY, initialValue = "")

    @OptIn(ExperimentalCoroutinesApi::class)
    val searchResults: StateFlow<List<PlaceData>> = searchQuery
        .mapLatest { SearchRepository.search(it) }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = emptyList()
        )

    fun onQueryChanged(query: String) {
        savedStateHandle[SEARCH_QUERY] = query
    }
}