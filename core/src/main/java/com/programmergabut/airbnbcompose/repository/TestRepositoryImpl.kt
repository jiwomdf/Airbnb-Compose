package com.programmergabut.airbnbcompose.repository

import com.programmergabut.airbnbcompose.core.dto.PostRequest
import com.programmergabut.airbnbcompose.core.dto.PostResponse
import com.programmergabut.airbnbcompose.di.network.HttpRoutes
import com.programmergabut.airbnbcompose.domain.model.Post
import com.programmergabut.airbnbcompose.util.ResponseResource
import com.programmergabut.airbnbcompose.util.setError
import com.programmergabut.airbnbcompose.util.setSuccess
import io.ktor.client.*
import io.ktor.client.features.*
import io.ktor.client.request.*
import io.ktor.http.*
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.Flow

class TestRepositoryImpl(
    private val client: HttpClient
) : TestRepository {

    override suspend fun getPosts(): Flow<ResponseResource<Post>> = flow {
        try {
            client.get<List<PostResponse>> {
                url(HttpRoutes.POSTS)
            }.let {
                emit(ResponseResource.Success(Post.mapPost(it)))
                //setSuccess(Post.mapPost(it))
            }
        } catch(e: RedirectResponseException) {
            // 3xx - responses
            //TODO JIWO emit(ResponseResource.error(Post(data = emptyList(), error = e.response.status.description)))
            setError(Post(data = emptyList(), error = e.response.status.description))
        } catch(e: ClientRequestException) {
            // 4xx - responses
            setError(Post(data = emptyList(), error = e.response.status.description))
        } catch(e: ServerResponseException) {
            // 5xx - responses
            setError(Post(data = emptyList(), error = e.response.status.description))
        } catch(e: Exception) {
            setError(Post(data = emptyList(), error = e.message.toString()))
        }
    }

    override suspend fun createPost(postRequest: PostRequest): PostResponse? {
        return try {
            client.post<PostResponse> {
                url(HttpRoutes.POSTS)
                contentType(ContentType.Application.Json)
                body = postRequest
            }
        } catch(e: RedirectResponseException) {
            // 3xx - responses
            println("Error: ${e.response.status.description}")
            null
        } catch(e: ClientRequestException) {
            // 4xx - responses
            println("Error: ${e.response.status.description}")
            null
        } catch(e: ServerResponseException) {
            // 5xx - responses
            println("Error: ${e.response.status.description}")
            null
        } catch(e: Exception) {
            println("Error: ${e.message}")
            null
        }
    }
}