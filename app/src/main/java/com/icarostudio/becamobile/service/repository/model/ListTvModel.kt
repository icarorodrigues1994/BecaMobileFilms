package com.icarostudio.becamobile.service.repository.model

import com.google.gson.annotations.SerializedName

class ListTvModel {
    @SerializedName("page")
    var page: String = ""

    @SerializedName("results")
    var results: List<TvModel> = listOf()
}