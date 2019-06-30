package com.valkaryne.appnews.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.paging.PagedList
import com.valkaryne.appnews.repository.model.NetworkState
import com.valkaryne.appnews.repository.model.NewsEntity
import com.valkaryne.appnews.repository.network.NewsNetwork

class NewsRepository {

    private val boundaryCallback: PagedList.BoundaryCallback<NewsEntity>  by lazy {
        object : PagedList.BoundaryCallback<NewsEntity>() {
            override fun onZeroItemsLoaded() {
                super.onZeroItemsLoaded()
                // Load data from database
            }
        }
    }

    private val network: NewsNetwork = NewsNetwork(boundaryCallback)
    private val liveDataMerger: MediatorLiveData<PagedList<NewsEntity>> = MediatorLiveData()

    init {
        liveDataMerger.addSource(network.newsPaged) { value ->
            liveDataMerger.postValue(value)
        }
    }

    fun getNews(): LiveData<PagedList<NewsEntity>> = liveDataMerger

    fun getNetworkState(): LiveData<NetworkState> = network.networkState
}