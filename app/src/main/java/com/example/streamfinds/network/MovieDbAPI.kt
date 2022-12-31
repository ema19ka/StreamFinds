package com.example.streamfinds.network

import com.example.streamfinds.model.FindDTO
import retrofit2.http.GET

interface MovieDbAPI {
    @GET("/find/{api_key}")
    suspend fun findItems(): List<FindDTO>
}

