package com.icarostudio.becamobile.service.repository.model

import com.google.gson.annotations.SerializedName

class VideoModel {

    @SerializedName("results")
    var results: List<ResultsVideos> = listOf()
}