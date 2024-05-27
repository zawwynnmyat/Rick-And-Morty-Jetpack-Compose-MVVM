package com.zawmyat.rickandmortyuniverse.models.api


import com.zawmyat.rickandmortyuniverse.models.data_classes.character.Character
import com.zawmyat.rickandmortyuniverse.models.data_classes.character.CharacterResponse
import com.zawmyat.rickandmortyuniverse.models.data_classes.episode.EpisodeResponse
import com.zawmyat.rickandmortyuniverse.models.data_classes.location.LocationResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("character")
    suspend fun getCharacters(@Query("page") pageNumber:Int) : CharacterResponse

    @GET("character/{id}")
    suspend fun getSingleCharacter(@Path("id") charId :Int) : Character

    @GET("location")
    suspend fun getLocations(@Query("page") pageNumber: Int) : LocationResponse

    @GET("character")
    suspend fun searchCharacters(
        @Query("page") pageNumber: Int,
        @Query("name") name: String,
    ) : CharacterResponse

    @GET("episode")
    suspend fun getEpisodes(@Query("page") pageNumber: Int) : EpisodeResponse
}