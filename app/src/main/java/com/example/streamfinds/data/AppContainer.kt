package com.example.streamfinds.data

import com.example.streamfinds.network.MovieDbAPI
import retrofit2.Retrofit
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType

/**
 * Dependency Injection container at the application level.
 */
interface AppContainer {
    val streamFindsRepository: StreamFindsRepository
}

/**
 * Implementation for the Dependency Injection container at the application level.
 *
 * Variables are initialized lazily and the same instance is shared across the whole app.
 */
class DefaultAppContainer(override val streamFindsRepository: StreamFindsRepository) : AppContainer {
    private val BASE_URL = "https://api.themoviedb.org/3/"

    /**
     * Use the Retrofit builder to build a retrofit object using a kotlinx.serialization converter
     */
    private val retrofit: Retrofit = Retrofit.Builder()
        .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
        .baseUrl(BASE_URL)
        .build()

    /**
     * Retrofit service object for creating api calls
     */
    private val retrofitService: MovieDbAPI by lazy {
        retrofit.create(MovieDbAPI::class.java)
    }

    /**
     * DI implementation for Mars photos repository
     */
     val streamFindsRepo: StreamFindsRepository by lazy {
        NetworkStreamFindsRepository(retrofitService)
    }
}
