package com.icarostudio.becamobile.service.repository.model

import com.google.gson.annotations.SerializedName

class ListFilmsModel {

    @SerializedName("page")
    var page: String = ""

    @SerializedName("results")
    var results: List<FilmModel> = listOf()
}