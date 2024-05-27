package com.zawmyat.rickandmortyuniverse.models.repository

import android.util.Log
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.zawmyat.rickandmortyuniverse.models.api.ApiService
import com.zawmyat.rickandmortyuniverse.models.data_classes.character.Character
import com.zawmyat.rickandmortyuniverse.models.pagination.CharactersPagingSource
import com.zawmyat.rickandmortyuniverse.models.pagination.EpisodePagingSource
import com.zawmyat.rickandmortyuniverse.models.pagination.LocationPagingSource
import com.zawmyat.rickandmortyuniverse.models.pagination.SearchCharacterPagingSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.emptyFlow
import javax.inject.Inject

class ApiRepository @Inject constructor(
    private val apiService: ApiService
) {

    fun getCharacters() = Pager(
            config = PagingConfig(
                pageSize = 20
            ),
            pagingSourceFactory = {
                CharactersPagingSource(apiService)
            }
        ).flow

    suspend fun getSingleCharacter(id: Int) : Character {
        return apiService.getSingleCharacter(id)
    }

    fun getLocations() = Pager(
        config = PagingConfig(
            pageSize = 20
        ),
        pagingSourceFactory = {
            LocationPagingSource(apiService)
        }
    ).flow

    fun searchCharacter(query: String) = Pager(
        config = PagingConfig(
            pageSize = 20
        ),
        pagingSourceFactory = {
            SearchCharacterPagingSource(
                apiService = apiService,
                query = query
            )
        }
    ).flow

    fun getEpisodes() = Pager(
        config = PagingConfig(
            pageSize = 20
        ),
        pagingSourceFactory = {
            EpisodePagingSource(
                apiService = apiService
            )
        }
    ).flow
}