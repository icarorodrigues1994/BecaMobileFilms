package com.icarostudio.becamobile.service.repository.model

import com.google.gson.annotations.SerializedName

class PersonModel {
    @SerializedName("id")
    var id: Int = 0

    @SerializedName("original_title")
    var originalTitle: String = ""

    @SerializedName("release_date")
    var releaseDate: String = ""

    @SerializedName("genre_ids")
    var genresIds: List<Int> = listOf()

    @SerializedName("genres")
    var genres: List<GenreModel> = listOf()

    @SerializedName("overview")
    var overview: String = ""

    @SerializedName("poster_path")
    var posterPath: String = ""

    @SerializedName("backdrop_path")
    var backdropPath: String = ""

    @SerializedName("videos")
    lateinit var videos: VideoModel


    var genreDescription: String = ""

    var releaseDateFormat: String = ""
}