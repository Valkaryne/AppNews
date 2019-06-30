package com.valkaryne.appnews.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.valkaryne.appnews.repository.NewsRepository

class NewsListViewModel : ViewModel() {

    private val repository = NewsRepository()

    val networkState = repository.getNetworkState()
    val news = repository.getNews()

    fun refresh() {
        news.value!!.dataSource.invalidate()
    }
}