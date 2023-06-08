package com.ileitelabs.comicscomposeapp.data.repository

import com.ileitelabs.comicscomposeapp.data.CharactersResponse
import com.ileitelabs.comicscomposeapp.data.remote.MarvelApi
import com.ileitelabs.comicscomposeapp.data.remote.NetworkResult
import kotlinx.coroutines.flow.MutableStateFlow
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MarvelComicsRepository(private val api: MarvelApi) {
    val characters = MutableStateFlow<NetworkResult<CharactersResponse>>(NetworkResult.Initial())

    fun query(query: String) {
        characters.value = NetworkResult.Loading()
        api.getCharacters(query)
            .enqueue(object : Callback<CharactersResponse> {
                override fun onResponse(
                    call: Call<CharactersResponse>,
                    response: Response<CharactersResponse>
                ) {
                    if (response.isSuccessful) {
                        response.body()?.let {
                            characters.value = NetworkResult.Success(it)
                        }
                    } else {
                        characters.value = NetworkResult.Error(response.message())
                    }
                }

                override fun onFailure(call: Call<CharactersResponse>, t: Throwable) {
                    t.localizedMessage?.let {
                        characters.value = NetworkResult.Error(it)
                    }
                }
            })
    }
}