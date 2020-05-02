package com.bsktech.codingassignmentjet2travel.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Articles(
    @SerializedName("id") val id: String,
    @SerializedName("createdAt") val createdAt: String,
    @SerializedName("content") val content: String,
    @SerializedName("comments") val comments: Int,
    @SerializedName("likes") val likes: Int,
    @SerializedName("media") val media: List<Media>,
    @SerializedName("user") val user: List<User>
) : Serializable