package com.programmergabut.airbnbcompose.repository

import com.programmergabut.airbnbcompose.core.dto.collections.CollectionsResponse
import com.programmergabut.airbnbcompose.di.network.HttpRoutes
import com.programmergabut.airbnbcompose.domain.model.PlacesCard
import com.programmergabut.airbnbcompose.util.Resources
import com.programmergabut.airbnbcompose.util.ResponseResource
import com.programmergabut.airbnbcompose.util.setError
import com.programmergabut.airbnbcompose.util.setSuccess
import io.ktor.client.*
import io.ktor.client.features.*
import io.ktor.client.request.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class TestRepositoryImpl(
    private val client: HttpClient,
    private val clientId: String
) : TestRepository {

    override suspend fun getCollections(
        query: String,
        page: Int,
        perPage: Int
    ): Flow<ResponseResource<PlacesCard>> = flow {
        try {
            client.get<CollectionsResponse> {
                header("Authorization", "Client-ID $clientId")
                url(urlString = "${HttpRoutes.SEARCH_COLLECTIONS}/?query=$query&page=$page&per_page=$perPage")
            }.let {
                emit(setSuccess(PlacesCard.mapPost(it)))
            }
        } catch(e: RedirectResponseException) {
            // 3xx - responses
            emit(setError(PlacesCard(data = emptyList(), error = e.response.status.description, state = Resources.Error)))
        } catch(e: ClientRequestException) {
            // 4xx - responses
            emit(setError(PlacesCard(data = emptyList(), error = e.response.status.description, state = Resources.Error)))
        } catch(e: ServerResponseException) {
            // 5xx - responses
            emit(setError(PlacesCard(data = emptyList(), error = e.response.status.description, state = Resources.Error)))
        } catch(e: Exception) {
            emit(setError(PlacesCard(data = emptyList(), error = e.message.toString(), state = Resources.Error)))
        }
    }
}