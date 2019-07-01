package com.valkaryne.appnews.repository.network

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.valkaryne.appnews.NUMBER_OF_THREADS
import com.valkaryne.appnews.PAGE_SIZE
import com.valkaryne.appnews.repository.model.NetworkState
import com.valkaryne.appnews.repository.model.NewsEntity
import com.valkaryne.appnews.repository.network.paging.NewsNetworkDataSourceFactory
import java.util.concurrent.Executors

/**
 * Part of repository responsible for fetching data from network
 *
 * @author Valentine Litvin
 */
class NewsNetwork(
    dataSourceFactory: NewsNetworkDataSourceFactory,
    boundaryCallback: PagedList.BoundaryCallback<NewsEntity>
) {

    val newsPaged: LiveData<PagedList<NewsEntity>>
    val networkState: LiveData<NetworkState>

    init {
        val pagedListConfig = (PagedList.Config.Builder()).setEnablePlaceholders(false)
            .setInitialLoadSizeHint(PAGE_SIZE).setPageSize(PAGE_SIZE).build()
        networkState = Transformations.switchMap(dataSourceFactory.sourceLiveData) {
            it.getNetworkState()
        }
        val executor = Executors.newFixedThreadPool(NUMBER_OF_THREADS)
        val livePagedListBuilder = LivePagedListBuilder(dataSourceFactory, pagedListConfig)
        newsPaged = livePagedListBuilder
            .setFetchExecutor(executor)
            .setBoundaryCallback(boundaryCallback)
            .build()
    }
}