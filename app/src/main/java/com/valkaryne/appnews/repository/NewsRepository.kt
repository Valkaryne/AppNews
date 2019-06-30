package com.valkaryne.appnews.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.paging.PagedList
import com.valkaryne.appnews.repository.model.NewsEntity
import com.valkaryne.appnews.repository.network.NewsNetwork
import com.valkaryne.appnews.repository.network.paging.NewsNetworkDataSourceFactory
import com.valkaryne.appnews.repository.room.NewsDatabase

class NewsRepository(private val database: NewsDatabase) {

    private val boundaryCallback: PagedList.BoundaryCallback<NewsEntity>  by lazy {
        object : PagedList.BoundaryCallback<NewsEntity>() {
            override fun onZeroItemsLoaded() {
                super.onZeroItemsLoaded()
                liveDataMerger.addSource(database.newsPaged) { value ->
                    liveDataMerger.postValue(value)
                    liveDataMerger.removeSource(database.newsPaged)
                }
            }
        }
    }

    private val dataSourceFactory = NewsNetworkDataSourceFactory()
    private val network: NewsNetwork = NewsNetwork(dataSourceFactory, boundaryCallback)
    private val liveDataMerger: MediatorLiveData<PagedList<NewsEntity>> = MediatorLiveData()

    init {
        liveDataMerger.addSource(network.newsPaged) { value ->
            liveDataMerger.postValue(value)
        }
    }

    fun getNews(): LiveData<PagedList<NewsEntity>> = liveDataMerger

    fun getNetworkState() = network.networkState

    fun insertNewsToDB(list: List<NewsEntity>?) {
        Thread {
            list?.forEach { database.newsDao().insertNews(it) }
        }.start()
    }
}