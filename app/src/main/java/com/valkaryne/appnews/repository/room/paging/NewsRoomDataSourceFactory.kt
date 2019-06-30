package com.valkaryne.appnews.repository.room.paging

import androidx.paging.DataSource
import com.valkaryne.appnews.repository.model.NewsEntity
import com.valkaryne.appnews.repository.room.NewsDao

class NewsRoomDataSourceFactory(private val dao: NewsDao) : DataSource.Factory<String, NewsEntity>() {
    override fun create(): DataSource<String, NewsEntity> = NewsRoomDataSource(dao)
}