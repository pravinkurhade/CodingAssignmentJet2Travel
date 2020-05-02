package com.bsktech.codingassignmentjet2travel.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Media(
    @SerializedName("id") val id: String,
    @SerializedName("blogId") val blogId: String,
    @SerializedName("createdAt") val createdAt: String,
    @SerializedName("image") val image: String,
    @SerializedName("title") val title: String,
    @SerializedName("url") val url: String
) : Serializable