package com.programmergabut.airbnbcompose.repository

import com.programmergabut.airbnbcompose.core.dto.PostRequest
import com.programmergabut.airbnbcompose.core.dto.PostResponse
import com.programmergabut.airbnbcompose.domain.model.Post
import com.programmergabut.airbnbcompose.util.ResponseResource
import kotlinx.coroutines.flow.Flow

interface TestRepository {

    suspend fun getPosts(): Flow<ResponseResource<Post>>

    suspend fun createPost(postRequest: PostRequest): PostResponse?
}