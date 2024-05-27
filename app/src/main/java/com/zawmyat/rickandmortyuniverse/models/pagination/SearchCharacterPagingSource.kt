package com.zawmyat.rickandmortyuniverse.models.pagination

import android.util.Log
import androidx.paging.Pager
import androidx.paging.PagingData
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.zawmyat.rickandmortyuniverse.models.api.ApiService
import com.zawmyat.rickandmortyuniverse.models.data_classes.character.Character
import kotlinx.coroutines.flow.emptyFlow

class SearchCharacterPagingSource(
    private val apiService: ApiService,
    private val query: String
) : PagingSource<Int, Character>() {

    override fun getRefreshKey(state: PagingState<Int, Character>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Character> {
        return try {
            val page = params.key ?: 1

            val response = apiService.searchCharacters(
                name = query,
                pageNumber = page
            )

            Log.i("response", response.results.toString())

            LoadResult.Page(
                data = response.results,
                prevKey = if (page == 1) null else page.minus(1),
                nextKey = if (response.results.isEmpty()) null else page.plus(1),
            )

        } catch (e: Exception) {

//            Log.e("error", e.message.toString())
//            LoadResult.Error(e)
            LoadResult.Page(
                data = emptyList(),
                prevKey = null,
                nextKey = null,
            )
        }
    }
}