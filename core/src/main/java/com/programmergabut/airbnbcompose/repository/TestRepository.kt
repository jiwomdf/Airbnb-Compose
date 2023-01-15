package com.programmergabut.airbnbcompose.repository

import com.programmergabut.airbnbcompose.domain.model.PlacesCard
import com.programmergabut.airbnbcompose.util.ResponseResource
import kotlinx.coroutines.flow.Flow

interface TestRepository {

    suspend fun getCollections(
        query: String,
        page: Int,
        perPage: Int
    ): Flow<ResponseResource<PlacesCard>>
}