package com.example.streamfinds.model



data class SearchDTO(
    val result: Movie
)


data class Movie(
    val id: Int,
    val posterPath: String,
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



