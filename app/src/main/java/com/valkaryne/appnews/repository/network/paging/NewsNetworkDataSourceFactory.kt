package com.valkaryne.appnews.repository.network.paging

import androidx.paging.DataSource
import com.valkaryne.appnews.repository.model.NewsEntity

class NewsNetworkDataSourceFactory : DataSource.Factory<String, NewsEntity>() {

    private val dataSource = NewsNetworkDataSource()

    val news = dataSource.getNewsData()

    val state = dataSource.getNetworkState()

    override fun create(): DataSource<String, NewsEntity> = dataSource
}