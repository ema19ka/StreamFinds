package com.example.streamfinds.model

import com.google.gson.annotations.SerializedName


data class Movie(
    @SerializedName("id") val id: Int,
    @SerializedName("original_title") val title: String,
    @SerializedName("poster_path") val posterPath: String,
    @SerializedName("release_date") val releaseDate: String
)

data class GetMoviesResponse(
    @SerializedName("page") val page: Int,
    @SerializedName("results") val movies: List<Movie>,
    @SerializedName("total_pages") val pages: Int
)

data class GetProviders(
    @SerializedName("id") val id: Int,
    @SerializedName("results") val providers: List<ProviderCountry>
)

data class ProviderCountry(
    @SerializedName("AT") val at: Provider,
)

data class Provider(
    @SerializedName("link") val link: String,

)


