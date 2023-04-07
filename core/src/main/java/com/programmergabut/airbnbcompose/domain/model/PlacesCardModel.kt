package com.programmergabut.airbnbcompose.domain.model

import android.os.Parcelable
import com.programmergabut.airbnbcompose.core.dto.collections.CollectionsResponse
import com.programmergabut.airbnbcompose.util.Resources
import com.programmergabut.airbnbcompose.util.ran
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

@Parcelize
data class PlacesCardModel(
    val state: @RawValue Resources = Resources.Loading,
    val listPlaces: @RawValue List<PlacesCardData> = emptyList(),
    val error: String = ""
): Parcelable {

    @Parcelize
    data class PlacesCardData(
        val id: String,
        val imgUrl: String,
        val title: String,
        val dsc: String,
        val distance: String,
        val date: String,
        val price: Int,
        val like: Int,
        val owner: String,
        val ownerBio: String,
        val ownerLocation: String
    ): Parcelable

    companion object {
        fun mapPost(response: CollectionsResponse): PlacesCardModel {
            val listResult = mutableListOf<PlacesCardData>()
            response.results?.forEach {
                listResult.add(PlacesCardData(
                    id = it?.id ?: "",
                    imgUrl = it?.cover_photo?.urls?.small ?: "",
                    title = it?.title ?: "",
                    dsc = it?.cover_photo?.description ?: "",
                    distance = (ran(10,1000)).toString(),
                    date = it?.published_at ?: "",
                    price = ran(10,100),
                    like = it?.cover_photo?.likes ?: 0,
                    owner = it?.user?.username ?: "",
                    ownerBio = it?.user?.bio ?: "",
                    ownerLocation = it?.user?.location ?: ""
                ))
            }
            return PlacesCardModel(
                listPlaces = listResult,
                state = Resources.Success
            )
        }
    }

}
