package com.bsktech.codingassignmentjet2travel.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.bsktech.codingassignmentjet2travel.models.Articles
import com.bsktech.codingassignmentjet2travel.repository.Repository
import com.bsktech.codingassignmentjet2travel.utils.Constants

class MainViewModel : ViewModel() {
    var articles: LiveData<List<Articles>>? = null
    fun getArticles(pageNumber: Int) {
        articles = Repository.getArticles(pageNumber, Constants.LIMIT)
    }
}