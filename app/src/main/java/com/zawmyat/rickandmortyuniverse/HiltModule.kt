package com.zawmyat.rickandmortyuniverse

import android.content.Context
import androidx.room.Room
import com.zawmyat.rickandmortyuniverse.models.api.ApiService
import com.zawmyat.rickandmortyuniverse.models.repository.ApiRepository
import com.zawmyat.rickandmortyuniverse.models.room.dao.FavoriteCharacterDao
import com.zawmyat.rickandmortyuniverse.models.room.database.AppDatabase
import com.zawmyat.rickandmortyuniverse.models.room.repository.FavoriteCharacterRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(ViewModelComponent::class)
class HiltModule {
    @Provides
    fun privateCharactersRepository(apiService: ApiService) = ApiRepository(apiService)

}