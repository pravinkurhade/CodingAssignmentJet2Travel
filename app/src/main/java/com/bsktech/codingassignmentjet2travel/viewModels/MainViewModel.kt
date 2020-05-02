package com.bsktech.codingassignmentjet2travel.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {

    private val page: MutableLiveData<Int> = MutableLiveData()
    private var limit = 10

    fun getArticles(pageNumber: Int) {
        page.value == pageNumber
    }

}