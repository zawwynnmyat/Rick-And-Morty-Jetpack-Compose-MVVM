package com.zawmyat.rickandmortyuniverse.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.zawmyat.rickandmortyuniverse.models.data_classes.character.Character
import com.zawmyat.rickandmortyuniverse.models.data_classes.character.CharacterResponse
import com.zawmyat.rickandmortyuniverse.models.repository.ApiRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class SearchCharacterViewModel @Inject constructor(
    private val repository: ApiRepository
): ViewModel() {

    val queryFlow = MutableStateFlow("")

    @OptIn(ExperimentalCoroutinesApi::class)
    val searchResults : Flow<PagingData<Character>> = queryFlow
        .flatMapLatest {
            query ->
            repository.searchCharacter(query = query)
        }.cachedIn(viewModelScope)

    fun onSearchQueryChange(newQuery: String) {
        queryFlow.value = newQuery
    }
}