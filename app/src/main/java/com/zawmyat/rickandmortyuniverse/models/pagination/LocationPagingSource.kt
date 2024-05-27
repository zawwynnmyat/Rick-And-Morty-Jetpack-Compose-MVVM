package com.zawmyat.rickandmortyuniverse.models.pagination

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.zawmyat.rickandmortyuniverse.models.api.ApiService
import com.zawmyat.rickandmortyuniverse.models.data_classes.location.Location


class LocationPagingSource(
    private val apiService: ApiService
) : PagingSource<Int, Location>() {

    override fun getRefreshKey(state: PagingState<Int, Location>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Location> {
        return try {
            val page = params.key ?: 1
            val response = apiService.getLocations(pageNumber = page)

            LoadResult.Page(
                data = response.results,
                prevKey = if (page == 1) null else page.minus(1),
                nextKey = if (response.results.isEmpty()) null else page.plus(1),
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

}