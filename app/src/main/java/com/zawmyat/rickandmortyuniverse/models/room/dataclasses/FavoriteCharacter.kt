package com.zawmyat.rickandmortyuniverse.models.room.dataclasses

import androidx.compose.runtime.Composable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class FavoriteCharacter(
    @ColumnInfo(name = "name") val name: String?,
    @ColumnInfo(name = "avatar") val avatar: String?,
    @ColumnInfo(name = "status") val status: String?,
    @ColumnInfo(name = "species") val species: String?,
    @ColumnInfo(name = "gender") val gender: String?,
    @PrimaryKey(autoGenerate = false) val characterId: Int?
)
