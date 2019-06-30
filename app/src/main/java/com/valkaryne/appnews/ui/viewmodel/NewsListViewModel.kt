package com.valkaryne.appnews.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.valkaryne.appnews.repository.NewsRepository

class NewsListViewModel : ViewModel() {

    private val repository = NewsRepository()

    val news = repository.getNews()

    val networkState = repository.getNetworkState()
}