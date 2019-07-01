package com.valkaryne.appnews.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.valkaryne.appnews.repository.NewsRepository
import com.valkaryne.appnews.repository.network.paging.NewsNetworkDataSource

/**
 * [NewsListViewModel] is used as communication means between NewsRepository and NewsListFragment
 *
 * @author Valentine Litvin
 */
class NewsListViewModel(private val repository: NewsRepository) : ViewModel() {

    val networkState = repository.getNetworkState()
    val news = repository.getNews()

    /**
     * Refreshes content of news LiveData
     */
    fun refresh() {
        news.value!!.dataSource.invalidate()
    }

    /**
     * Saves fetched news data to cache
     */
    fun saveNewsToCache() {
        repository.insertNewsToDB((news.value!!.dataSource as NewsNetworkDataSource).getNews().value)
    }
}