package com.programmergabut.airbnbcompose.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.programmergabut.airbnbcompose.domain.model.FeatureModel
import com.programmergabut.airbnbcompose.domain.model.PlacesCardModel
import com.programmergabut.airbnbcompose.domain.model.SettingModel
import com.programmergabut.airbnbcompose.repository.PlacesRepository
import com.programmergabut.airbnbcompose.paging.PlacesPagingSource
import com.programmergabut.airbnbcompose.util.generateFeature
import com.programmergabut.airbnbcompose.util.generateSetting
import com.programmergabut.airbnbcompose.util.ran
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

interface IPlacesViewModel{
    fun getPlacesPage(
        query: String,
        orderBy: String,
        orientation: String,
        color: String,
        perPage: Int
    ): Flow<PagingData<PlacesCardModel.PlacesCardData>>
    fun getSettings(): List<SettingModel>
    fun getFeatures(): List<FeatureModel>
}

class FakePlacesViewModel : IPlacesViewModel {
    override fun getPlacesPage(
        query: String,
        orderBy: String,
        orientation: String,
        color: String,
        perPage: Int
    ): Flow<PagingData<PlacesCardModel.PlacesCardData>> = flow {  }

    override fun getSettings(): List<SettingModel> = generateSetting()
    override fun getFeatures(): List<FeatureModel> = generateFeature()
}

class PlacesViewModel(
    private val repository: PlacesRepository,
): ViewModel(), IPlacesViewModel {

    override fun getPlacesPage(
        query: String,
        orderBy: String,
        orientation: String,
        color: String,
        perPage: Int,
    ): Flow<PagingData<PlacesCardModel.PlacesCardData>> {
        return Pager(
            config = PagingConfig(
                pageSize = perPage,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { PlacesPagingSource(
                query = query,
                orderBy = orderBy,
                orientation = orientation,
                color = color,
                repository = repository,
                perPage = perPage,
            ) }
        )
            .flow
            .cachedIn(viewModelScope)
    }

    override fun getSettings(): List<SettingModel> = generateSetting()
    override fun getFeatures(): List<FeatureModel>  = generateFeature().shuffled().take(ran(1, 3))

}