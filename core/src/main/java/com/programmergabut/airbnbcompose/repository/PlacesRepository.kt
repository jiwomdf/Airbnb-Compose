package com.programmergabut.airbnbcompose.repository

import com.programmergabut.airbnbcompose.domain.model.PlacesCardModel
import com.programmergabut.airbnbcompose.util.ResponseResource

interface PlacesRepository {

    suspend fun getPlaces(
        query: String,
        page: Int,
        perPage: Int
    ): ResponseResource<PlacesCardModel>
}