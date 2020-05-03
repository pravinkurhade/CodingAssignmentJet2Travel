package com.bsktech.codingassignmentjet2travel.repository

import androidx.lifecycle.LiveData
import com.bsktech.codingassignmentjet2travel.apis.MyRetrofitBuilder
import com.bsktech.codingassignmentjet2travel.models.Articles
import kotlinx.coroutines.*

object Repository {

    fun getArticles(page: Int, limit: Int): LiveData<List<Articles>> {
        val job = Job()
        return object : LiveData<List<Articles>>() {
            override fun onActive() {
                super.onActive()
                job.let { theJob ->
                    CoroutineScope(Dispatchers.IO + theJob).launch {
                        try {
                            val articles = MyRetrofitBuilder.apiService.getArticles(page, limit)
                            withContext(Dispatchers.Main) {
                                value = articles
                                theJob.complete()
                            }
                        } catch (e: Exception) {
                            e.printStackTrace()
                        }
                    }
                }
            }
        }
    }
}