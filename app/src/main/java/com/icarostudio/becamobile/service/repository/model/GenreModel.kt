package com.icarostudio.becamobile.service.repository.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "Genrer")
class GenreModel {

    @SerializedName("id")
    @ColumnInfo(name = "id")
    @PrimaryKey
    var id: Int = 0

    @SerializedName("name")
    @ColumnInfo(name = "name")
    var name: String = ""
}