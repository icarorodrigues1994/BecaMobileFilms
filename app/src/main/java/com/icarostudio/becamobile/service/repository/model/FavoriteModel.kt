package com.icarostudio.becamobile.service.repository.model

import com.google.gson.annotations.SerializedName

class FavoriteModel {
    @SerializedName("success")
    var success: Boolean = false

    @SerializedName("status_code")
    var statusCode: Int = 0

    @SerializedName("status_message")
    var statusMessage: String = ""
}