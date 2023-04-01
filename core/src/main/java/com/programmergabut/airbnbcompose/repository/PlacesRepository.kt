package com.programmergabut.airbnbcompose.repository

import com.programmergabut.airbnbcompose.domain.model.PlacesCard
import com.programmergabut.airbnbcompose.util.ResponseResource
import kotlinx.coroutines.flow.Flow

interface PlacesRepository {

    suspend fun getPlaces(
        query: String,
        page: Int,
        perPage: Int
    ): ResponseResource<PlacesCard>
}