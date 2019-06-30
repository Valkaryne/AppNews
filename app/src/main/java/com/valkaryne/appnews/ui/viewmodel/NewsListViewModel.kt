package com.valkaryne.appnews.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.valkaryne.appnews.repository.NewsRepository
import com.valkaryne.appnews.repository.network.paging.NewsNetworkDataSource

class NewsListViewModel(private val repository: NewsRepository) : ViewModel() {

    val networkState = repository.getNetworkState()
    val news = repository.getNews()

    fun refresh() {
        news.value!!.dataSource.invalidate()
    }

    fun saveNewsToCache() {
        repository.insertNewsToDB((news.value!!.dataSource as NewsNetworkDataSource).getNews().value)
    }
}