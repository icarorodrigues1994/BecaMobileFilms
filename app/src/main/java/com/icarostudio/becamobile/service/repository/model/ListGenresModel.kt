package com.icarostudio.becamobile.service.repository.model

import com.google.gson.annotations.SerializedName

class ListGenresModel {

    @SerializedName("genres")
    var genres: List<GenreModel> = listOf()

}