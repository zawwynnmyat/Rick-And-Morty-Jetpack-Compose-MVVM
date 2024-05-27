package com.zawmyat.rickandmortyuniverse.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zawmyat.rickandmortyuniverse.models.api.ApiService
import com.zawmyat.rickandmortyuniverse.models.data_classes.character.Character
import com.zawmyat.rickandmortyuniverse.models.repository.ApiRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SingleCharacterViewModel @Inject constructor(
    private val apiRepository: ApiRepository,
) : ViewModel() {

    private val _singleCharacter = MutableLiveData<Character>()
    val singleCharacter : LiveData<Character> get() = _singleCharacter

    private val _isFetchingCharacter = MutableLiveData<Boolean>()
    val isFetchingCharacter : LiveData<Boolean> get() = _isFetchingCharacter

    private val _fetchErrorMessage = MutableLiveData<String>()
    val fetchErrorMessage : LiveData<String> get() = _fetchErrorMessage

    fun getSingleCharacter(id: Int) {

        viewModelScope.launch(Dispatchers.IO) {

            try {

                _isFetchingCharacter.postValue(true)

                val chara = apiRepository.getSingleCharacter(id)
                _singleCharacter.postValue(chara)

                _isFetchingCharacter.postValue(false)
            } catch (e: Exception) {
                Log.e("character error", e.message.toString())
                _isFetchingCharacter.postValue(false)
                _fetchErrorMessage.postValue("Error fetching character: ${e.message}")
            }

        }
    }
}