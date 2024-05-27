package com.zawmyat.rickandmortyuniverse.models.room.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.zawmyat.rickandmortyuniverse.models.room.dataclasses.FavoriteCharacter
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteCharacterDao {

    @Query("SELECT * FROM favoritecharacter")
    suspend fun getAllFavoriteCharacters() : List<FavoriteCharacter>

    @Query("SELECT * FROM favoritecharacter ORDER BY name ASC")
    suspend fun orderByNameAsc() : List<FavoriteCharacter>

    @Query("SELECT * FROM favoritecharacter ORDER BY name DESC")
    suspend fun orderByNameDesc() : List<FavoriteCharacter>

    @Insert
    suspend fun addToFavorite(favoriteCharacter: FavoriteCharacter)

    @Delete
    suspend fun deleteCharacter(favoriteCharacter: FavoriteCharacter)
}