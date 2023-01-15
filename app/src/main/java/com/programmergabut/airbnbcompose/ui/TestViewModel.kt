package com.programmergabut.airbnbcompose.ui

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.programmergabut.airbnbcompose.repository.TestRepository
import com.programmergabut.airbnbcompose.domain.model.PlacesCard
import com.programmergabut.airbnbcompose.util.ResponseResource
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class TestViewModel(
    private val repository: TestRepository,
): ViewModel() {
    private val _placesCardResponse = mutableStateOf(PlacesCard())
    val placesCardResponse: State<PlacesCard> = _placesCardResponse

    fun getCollections(
        query: String,
        page: Int,
        perPage: Int
    ) {
        _placesCardResponse.value = PlacesCard()
        viewModelScope.launch {
            repository.getCollections(
                query = query,
                page = page,
                perPage = perPage
            ).onEach {
                when (it) {
                    is ResponseResource.Error ->
                        _placesCardResponse.value = it.errorMessage
                    is ResponseResource.Success ->
                        _placesCardResponse.value = it.data
                }
            }.launchIn(viewModelScope)
        }
    }
}