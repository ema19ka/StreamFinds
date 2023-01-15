package com.example.streamfinds.model

import com.google.gson.annotations.SerializedName


data class Movie(
    @SerializedName("id") val id: Int,
    @SerializedName("original_title") val title: String,
    @SerializedName("poster_path") val posterPath: String,
)

data class GetMoviesResponse(
    @SerializedName("results") val movies: List<Movie>,
)

data class MovieDetails(
    @SerializedName("id") val id: Int,
    @SerializedName("original_title") val title: String,
    @SerializedName("poster_path") val posterPath: String?,
    @SerializedName("release_date") val releaseDate: String,
    @SerializedName("original_language") val lang: String,
    @SerializedName("title") val enTitle: String,
)

data class Show(
    @SerializedName("id") val id: Int,
    @SerializedName("original_name") val title: String,
    @SerializedName("poster_path") val posterPath: String,
)

data class GetShowsResponse(
    @SerializedName("results") val shows: List<Show>,
)

data class ShowDetails(
    @SerializedName("id") val id: Int,
    @SerializedName("original_name") val title: String,
    @SerializedName("poster_path") val posterPath: String?,
    @SerializedName("first_air_date") val releaseDate: String,
    @SerializedName("original_language") val lang: String,
    @SerializedName("name") val enTitle: String,
)

data class GetProviders(
    @SerializedName("results") val providers: ProviderCountry
)

data class ProviderCountry(
    @SerializedName("AT") val at: Provider,
)

data class Provider(
    @SerializedName("flatrate") val streamService: List<StreamService>
)

data class StreamService(
    @SerializedName("logo_path") val logo: String,
    @SerializedName("provider_name") val name: String
)


