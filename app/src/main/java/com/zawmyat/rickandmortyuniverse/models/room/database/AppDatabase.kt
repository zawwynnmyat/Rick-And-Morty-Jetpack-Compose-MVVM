package com.zawmyat.rickandmortyuniverse.models.room.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.zawmyat.rickandmortyuniverse.models.room.dao.FavoriteCharacterDao
import com.zawmyat.rickandmortyuniverse.models.room.dataclasses.FavoriteCharacter

@Database(entities = [FavoriteCharacter::class], version = 2)
abstract class AppDatabase : RoomDatabase() {

    abstract fun favoriteCharacterDao(): FavoriteCharacterDao
}