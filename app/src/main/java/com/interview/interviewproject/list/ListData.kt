package com.interview.interviewproject.list

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class ListData : Serializable{
    @SerializedName("albumId")
    val albumId = 0
    @SerializedName("id")
    val id = 0
    @SerializedName("title")
    val title = ""
    @SerializedName("url")
    val url = ""
    @SerializedName("thumbnailUrl")
    val thumbnailUrl = ""
}