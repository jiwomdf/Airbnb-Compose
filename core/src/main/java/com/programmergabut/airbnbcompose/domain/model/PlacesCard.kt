package com.programmergabut.airbnbcompose.domain.model

import com.programmergabut.airbnbcompose.core.dto.collections.CollectionsResponse
import com.programmergabut.airbnbcompose.util.Resources


data class PlacesCard(
    val state: Resources = Resources.Loading,
    val data: List<PlacesCardData> = emptyList(),
    val error: String = ""
) {
    data class PlacesCardData(
        val imgUrl: String,
        val title: String,
        val distance: String,
        val date: String,
        val price: Int,
        val like: Int
    )

    companion object {
        fun mapPost(response: CollectionsResponse): PlacesCard {
            val listResult = mutableListOf<PlacesCardData>()
            response.results?.forEach {
                val result = PlacesCardData(
                    imgUrl = it?.cover_photo?.urls?.small ?: "",
                    title = it?.title ?: "",
                    distance = ((10..1000).random()).toString(),
                    date = it?.published_at ?: "",
                    price = (10..100).random(),
                    like = it?.cover_photo?.likes ?: 0
                )
                listResult.add(result)
            }
            return PlacesCard(
                data = listResult,
                state = Resources.Success
            )
        }
    }
}
