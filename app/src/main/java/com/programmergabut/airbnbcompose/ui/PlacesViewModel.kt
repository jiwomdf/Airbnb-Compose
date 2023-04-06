package com.programmergabut.airbnbcompose.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.programmergabut.airbnbcompose.domain.model.PlacesCard
import com.programmergabut.airbnbcompose.repository.PlacesRepository
import com.programmergabut.airbnbcompose.paging.PlacesPagingSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

interface IPlacesViewModel{
    fun getPlacesPage(query: String, page: Int, perPage: Int): Flow<PagingData<PlacesCard.PlacesCardData>>
}

class FakePlacesViewModel : IPlacesViewModel {
    override fun getPlacesPage(query: String, page: Int, perPage: Int):
            Flow<PagingData<PlacesCard.PlacesCardData>> = flow {  }
}

class PlacesViewModel(
    private val repository: PlacesRepository,
): ViewModel(), IPlacesViewModel {

    override fun getPlacesPage(
        query: String,
        page: Int,
        perPage: Int,
    ): Flow<PagingData<PlacesCard.PlacesCardData>> {
        return Pager(
            config = PagingConfig(
                pageSize = perPage,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { PlacesPagingSource(query, repository, perPage) }
        )
            .flow
            .cachedIn(viewModelScope)
    }

}