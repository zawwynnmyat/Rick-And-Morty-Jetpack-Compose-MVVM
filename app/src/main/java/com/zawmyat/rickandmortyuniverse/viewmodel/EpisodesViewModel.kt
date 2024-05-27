package com.zawmyat.rickandmortyuniverse.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.zawmyat.rickandmortyuniverse.models.data_classes.episode.Episode
import com.zawmyat.rickandmortyuniverse.models.repository.ApiRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class EpisodesViewModel @Inject constructor(
    private val apiRepository: ApiRepository
) : ViewModel() {

    fun getEpisodes() : Flow<PagingData<Episode>> = apiRepository.getEpisodes().cachedIn(viewModelScope)
}