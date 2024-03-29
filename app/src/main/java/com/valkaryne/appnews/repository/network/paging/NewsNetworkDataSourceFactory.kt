package com.valkaryne.appnews.repository.network.paging

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.valkaryne.appnews.repository.model.NewsEntity

/**
 * A Factory for network data source
 *
 * @author Valentine Litvin
 */
class NewsNetworkDataSourceFactory : DataSource.Factory<String, NewsEntity>() {

    val sourceLiveData = MutableLiveData<NewsNetworkDataSource>()

    override fun create(): DataSource<String, NewsEntity> {
        val dataSource = NewsNetworkDataSource()
        sourceLiveData.postValue(dataSource)
        return dataSource
    }
}