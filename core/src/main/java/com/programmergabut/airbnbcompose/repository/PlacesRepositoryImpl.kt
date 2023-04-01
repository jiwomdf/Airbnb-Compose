package com.programmergabut.airbnbcompose.repository

import com.programmergabut.airbnbcompose.core.dto.collections.CollectionsResponse
import com.programmergabut.airbnbcompose.di.network.HttpRoutes
import com.programmergabut.airbnbcompose.domain.model.PlacesCard
import com.programmergabut.airbnbcompose.util.Resources
import com.programmergabut.airbnbcompose.util.ResponseResource
import com.programmergabut.airbnbcompose.util.setError
import com.programmergabut.airbnbcompose.util.setSuccess
import io.ktor.client.*
import io.ktor.client.request.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class PlacesRepositoryImpl(
    private val client: HttpClient,
    private val clientId: String
) : PlacesRepository {

    override suspend fun getPlaces(
        query: String,
        page: Int,
        perPage: Int
    ): ResponseResource<PlacesCard> =
        try {
            client.get<CollectionsResponse> {
                header("Authorization", "Client-ID $clientId")
                url(urlString = "${HttpRoutes.SEARCH_COLLECTIONS}/?query=$query&page=$page&per_page=$perPage")
            }.let {
                setSuccess(PlacesCard.mapPost(it))
            }
        } catch(e: Exception) {
            setError(PlacesCard(listPlaces = emptyList(), error = e.message.toString(), state = Resources.Error))
        }
}