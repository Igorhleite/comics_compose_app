package com.ileitelabs.comicscomposeapp.data.remote

import com.ileitelabs.comicscomposeapp.BuildConfig
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.math.BigInteger
import java.security.MessageDigest

object ApiService {
    private const val BASE_URL = "http://gateway.marvel.com/v1/public/"


    private fun getRetrofit(): Retrofit {
        val ts = System.currentTimeMillis().toString()
        val apiSecret = BuildConfig.MARVEL_SECRET_KEY
        val apiKey = BuildConfig.MARVEL_KEY

        val hash = getHash(ts, apiSecret, apiKey)

        val clientInterceptor = Interceptor { chain ->
            var request = chain.request()
            val url = request.url.newBuilder()
                .addQueryParameter("ts", ts)
                .addQueryParameter("apikey", apiKey)
                .addQueryParameter("hash", hash)
                .build()
            request = request.newBuilder().url(url).build()
            chain.proceed(request)
        }

        val client = OkHttpClient.Builder().addInterceptor(interceptor = clientInterceptor).build()

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }

    val api = getRetrofit().create(MarvelApi::class.java)

    private fun getHash(timeStamp: String, privateKey: String, publicKey: String): String {
        val hashStr = timeStamp + privateKey + publicKey
        val md = MessageDigest.getInstance("MD5")
        return BigInteger(1, md.digest(hashStr.toByteArray()))
            .toString(16).padStart(32, '0')
    }
}