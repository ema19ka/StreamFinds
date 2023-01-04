package com.example.streamfinds.model

import com.google.gson.annotations.SerializedName


data class SearchDTO(
    val results: List<Movie>
)


data class Movie(
    val id: Int,
    @SerializedName("poster_path")
    val posterPath: String,
    @SerializedName("relase_date")
    val releaseDate: String,
    val title: String,
)
/*
data class TV(
    val id: Int,
    val poster: String,
    val firstAirDate: String,
    val title: String,
    val lang: String,
)*/



