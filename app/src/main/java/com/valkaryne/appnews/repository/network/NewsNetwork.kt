package com.valkaryne.appnews.repository.network

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.valkaryne.appnews.NUMBER_OF_THREADS
import com.valkaryne.appnews.PAGE_SIZE
import com.valkaryne.appnews.repository.model.NetworkState
import com.valkaryne.appnews.repository.model.NewsEntity
import com.valkaryne.appnews.repository.network.paging.NewsNetworkDataSourceFactory
import java.util.concurrent.Executors

@Suppress("UNCHECKED_CAST")
class NewsNetwork(boundaryCallback: PagedList.BoundaryCallback<NewsEntity>) {

    private val dataSourceFactory = NewsNetworkDataSourceFactory()
    var networkState: LiveData<NetworkState> = dataSourceFactory.state
    val newsPaged: LiveData<PagedList<NewsEntity>>

    init {
        val pagedListConfig = (PagedList.Config.Builder()).setEnablePlaceholders(false)
            .setInitialLoadSizeHint(PAGE_SIZE).setPageSize(PAGE_SIZE).build()

        val executor = Executors.newFixedThreadPool(NUMBER_OF_THREADS)
        val livePagedListBuilder = LivePagedListBuilder(dataSourceFactory, pagedListConfig)
        newsPaged = livePagedListBuilder
            .setFetchExecutor(executor)
            .setBoundaryCallback(boundaryCallback)
            .build()
    }
}