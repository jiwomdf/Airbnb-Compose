package com.programmergabut.airbnbcompose.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.programmergabut.airbnbcompose.domain.model.PlacesCardModel
import com.programmergabut.airbnbcompose.repository.PlacesRepository
import com.programmergabut.airbnbcompose.util.ResponseResource


class PlacesPagingSource(
    private val query: String,
    private val orderBy: String,
    private val orientation: String,
    private val color: String,
    private val repository: PlacesRepository,
    private val perPage: Int
): PagingSource<Int, PlacesCardModel.PlacesCardData>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PlacesCardModel.PlacesCardData> {
        val startingPage = 1
        val position = params.key ?: startingPage

        return try {
            when(val response = repository.getPlaces(
                query = query,
                orderBy = orderBy,
                orientation = orientation,
                color = color,
                page = position,
                perPage = perPage,
            )){
                is ResponseResource.Success -> {
                    if(response.data.listPlaces.isNotEmpty()){
                        LoadResult.Page(
                            data = response.data.listPlaces,
                            prevKey = if(position == startingPage) null else position - 1,
                            nextKey = if( /* position >= response.totalPages || */ response.data.listPlaces.isEmpty()) null else position + 1
                        )
                    } else {
                        LoadResult.Error(Exception())
                    }
                }
                is ResponseResource.Error -> {
                    LoadResult.Error(Exception(response.data.error))
                }
            }
        } catch (ex: Exception){
            LoadResult.Error(ex)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, PlacesCardModel.PlacesCardData>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}