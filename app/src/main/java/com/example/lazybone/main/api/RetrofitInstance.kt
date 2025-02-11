package com.example.lazybone.main.api

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

//Ensures a singleton instance for efficient API interaction.
object RetrofitInstance {
    private const val BASE_URL = "https://exercisedb.p.rapidapi.com"

    private val logging = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private val client = OkHttpClient.Builder()
        .addInterceptor(logging)
        .addInterceptor { chain ->
            val request = chain.request().newBuilder()
                .addHeader("X-RapidAPI-Key", "18bf928921msh664498f858bba6bp1ed026jsn7b9d1cd5518b")
                .addHeader("X-RapidAPI-Host", "exercisedb.p.rapidapi.com")
                .build()
            chain.proceed(request)
        }.build()


    val api: ExerciseApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()) // Convert JSON to objects
            .client(client)
            .build()
            .create(ExerciseApiService::class.java)
    }
}

