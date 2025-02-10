package com.example.lazybone.main.api

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

//Ensures a singleton instance for efficient API interaction.
object RetrofitInstance {
    private const val BASE_URL = "https://exercisedb.p.rapidapi.com/"

    private val client = OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY // Logs API responses
        }).build()

    val api: ExerciseApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()) // Convert JSON to objects
            .client(client)
            .build()
            .create(ExerciseApiService::class.java)
    }
}

