package com.ileitelabs.comicscomposeapp.data.remote

import com.ileitelabs.comicscomposeapp.data.CharactersResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MarvelApi {
    @GET("characters")
    fun getCharacters(@Query("nameStartsWith") name: String): Call<CharactersResponse>

}