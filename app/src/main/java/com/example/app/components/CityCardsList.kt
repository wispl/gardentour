package com.example.app.components

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.app.model.City

@Composable
fun CityCardsList(
    cities: List<City>,
    onCityClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(modifier) {
        items(cities) {
            CityCard(it, { onCityClick(it.name) })
        }
    }
}
