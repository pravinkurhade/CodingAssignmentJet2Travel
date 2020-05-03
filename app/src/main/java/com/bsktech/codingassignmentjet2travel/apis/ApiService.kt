package com.bsktech.codingassignmentjet2travel.apis

import com.bsktech.codingassignmentjet2travel.models.Articles
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("blogs")
    suspend fun getArticles(@Query("page") page: Int, @Query("limit") limit: Int): List<Articles>
}