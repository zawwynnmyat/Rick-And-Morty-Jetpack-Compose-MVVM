package com.zawmyat.rickandmortyuniverse.viewmodel

import android.content.Context
import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zawmyat.rickandmortyuniverse.models.AppContext
import com.zawmyat.rickandmortyuniverse.models.datastore.SortingStore
import com.zawmyat.rickandmortyuniverse.models.room.dataclasses.FavoriteCharacter
import com.zawmyat.rickandmortyuniverse.models.room.repository.FavoriteCharacterRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteCharacterViewModel @Inject constructor(
    private val repository: FavoriteCharacterRepository
) : ViewModel() {

    private val _favChars = mutableStateOf<List<FavoriteCharacter>>(emptyList())
    val favChars : State<List<FavoriteCharacter>> = _favChars

    private var _onLoading by mutableStateOf(false)
    val onLoading: Boolean get() = _onLoading


    init {
        when {
            AppContext.currentSortSetting == "Name (Ascending)" -> {
                orderByNameAsc()
            }
            AppContext.currentSortSetting == "Name (Descending)" -> {
                orderByNameDesc()
            }
            else -> {
                orderById()
            }
        }
    }


    fun orderByNameAsc() {
        viewModelScope.launch {
            _favChars.value = repository.orderByNameAsc()
        }
    }

    fun orderByNameDesc() {
        viewModelScope.launch {
            _favChars.value = repository.orderByNameDesc()
        }
    }

    fun orderById() {
        viewModelScope.launch {
            _favChars.value = repository.getAllFavCharacters()
        }
    }

    suspend fun addToFav(favoriteCharacter: FavoriteCharacter) {
        viewModelScope.launch {
            _onLoading = true
            try {
                repository.addToFavorite(favoriteCharacter)
            } catch (e: Exception) {
                Log.e("addError", e.message.toString())
            }
            _onLoading = false

            when {
                AppContext.currentSortSetting == "Name (Ascending)" -> {
                    _favChars.value = repository.orderByNameAsc()
                }
                AppContext.currentSortSetting == "Name (Descending)" -> {
                    _favChars.value = repository.orderByNameDesc()
                }
                else -> {
                    _favChars.value = repository.getAllFavCharacters()
                }
            }

        }
    }

    fun removeFav(favoriteCharacter: FavoriteCharacter) {
        viewModelScope.launch {
            try {
                repository.deleteFavCharacter(favoriteCharacter)
            } catch (e: Exception) {
                Log.e("deleteError", e.message.toString())
            }

            when {
                AppContext.currentSortSetting == "Name (Ascending)" -> {
                    _favChars.value = repository.orderByNameAsc()
                }
                AppContext.currentSortSetting == "Name (Descending)" -> {
                    _favChars.value = repository.orderByNameDesc()
                }
                else -> {
                    _favChars.value = repository.getAllFavCharacters()
                }
            }

        }
    }
}