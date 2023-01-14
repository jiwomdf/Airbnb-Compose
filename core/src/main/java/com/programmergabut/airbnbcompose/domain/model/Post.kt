package com.programmergabut.airbnbcompose.domain.model

import com.programmergabut.airbnbcompose.core.dto.PostResponse


data class Post(
    val isLoading: Boolean = false,
    val data: List<PostData> = emptyList(),
    val error: String = ""
) {
    data class PostData(
        val body: String,
        val title: String,
        val id: Int,
        val userId: Int
    )

    companion object {
        fun mapPost(response: List<PostResponse>): Post {
            val listResult = mutableListOf<PostData>()
            response.forEach {
                val result = PostData(
                    body = it.body,
                    title = it.title,
                    id = it.id,
                    userId = it.userId,
                )
                listResult.add(result)
            }
            return Post(data = listResult)
        }
    }
}
