package com.zawmyat.rickandmortyuniverse

import android.content.Context
import androidx.room.Room
import com.zawmyat.rickandmortyuniverse.models.AppContext.applicationContext
import com.zawmyat.rickandmortyuniverse.models.CacheInterceptor
import com.zawmyat.rickandmortyuniverse.models.api.ApiService
import com.zawmyat.rickandmortyuniverse.models.room.dao.FavoriteCharacterDao
import com.zawmyat.rickandmortyuniverse.models.room.database.AppDatabase
import com.zawmyat.rickandmortyuniverse.models.room.repository.FavoriteCharacterRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class SingletonModule {

    private val BASE_URL = "https://rickandmortyapi.com/api/"

    val okHttpClient : OkHttpClient by lazy {
        OkHttpClient().newBuilder()
            .cache(Cache(File(applicationContext.cacheDir, "http-cache"), 10L * 1024L * 1024L)) // 10 MiB
            .addNetworkInterceptor(CacheInterceptor())
            .build()
    }

    @Singleton
    @Provides
    fun provideRetrofitInstance() : ApiService  = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
            .create(ApiService::class.java)

    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext appContext: Context): AppDatabase {
        return Room.databaseBuilder(
            appContext,
            AppDatabase::class.java,
            "favoritecharacter"
        ).fallbackToDestructiveMigration().build()
    }

    @Singleton
    @Provides
    fun provideDao(appDatabase: AppDatabase) : FavoriteCharacterDao {
        return appDatabase.favoriteCharacterDao()
    }

    @Singleton
    @Provides
    fun provideTaskRepository(favDao: FavoriteCharacterDao): FavoriteCharacterRepository {
        return FavoriteCharacterRepository(favDao)
    }
}