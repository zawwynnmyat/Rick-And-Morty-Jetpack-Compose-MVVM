package com.zawmyat.rickandmortyuniverse.models.room.repository

import com.zawmyat.rickandmortyuniverse.models.room.dao.FavoriteCharacterDao
import com.zawmyat.rickandmortyuniverse.models.room.dataclasses.FavoriteCharacter
import javax.inject.Inject

class FavoriteCharacterRepository @Inject constructor(
    private val favoriteCharacterDao: FavoriteCharacterDao
) {

    suspend fun getAllFavCharacters() : List<FavoriteCharacter> {
        return favoriteCharacterDao.getAllFavoriteCharacters()
    }

    suspend fun addToFavorite(favoriteCharacter: FavoriteCharacter) {
        return favoriteCharacterDao.addToFavorite(favoriteCharacter)
    }

    suspend fun deleteFavCharacter(favoriteCharacter: FavoriteCharacter) {
        return favoriteCharacterDao.deleteCharacter(favoriteCharacter)
    }

    suspend fun orderByNameAsc() : List<FavoriteCharacter> {
        return favoriteCharacterDao.orderByNameAsc()
    }

    suspend fun orderByNameDesc() : List<FavoriteCharacter> {
        return favoriteCharacterDao.orderByNameDesc()
    }
}