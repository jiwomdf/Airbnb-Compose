package com.programmergabut.airbnbcompose.repository

import com.programmergabut.airbnbcompose.domain.model.PlacesCardModel
import com.programmergabut.airbnbcompose.util.ResponseResource

interface PlacesRepository {

    suspend fun getPlaces(
        query: String,
        orderBy: String,
        orientation: String,
        color: String,
        page: Int,
        perPage: Int
    ): ResponseResource<PlacesCardModel>
}