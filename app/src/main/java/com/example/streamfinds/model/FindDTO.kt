package com.example.streamfinds.model


data class FindDTO (
    val movies: List<Movie>,
    val shows: List<TV>,
)

data class Movie (
    val id: Int,
    val poster: String,
    val releaseDate: String,
    val title: String,
    val lang: String,
)

data class TV (
    val id: Int,
    val poster: String,
    val firstAirDate: String,
    val title: String,
    val lang: String,
)



