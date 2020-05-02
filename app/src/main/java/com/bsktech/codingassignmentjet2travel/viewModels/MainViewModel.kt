package com.bsktech.codingassignmentjet2travel.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.bsktech.codingassignmentjet2travel.models.Articles
import com.bsktech.codingassignmentjet2travel.repository.Repository

class MainViewModel : ViewModel() {
    private var limit = 10
    var articles: LiveData<List<Articles>>? = null
    fun getArticles(pageNumber: Int) {
        articles = Repository.getArticles(pageNumber, limit)
    }
}