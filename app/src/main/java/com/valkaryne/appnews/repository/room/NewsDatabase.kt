package com.valkaryne.appnews.repository.room

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import androidx.room.Database
import androidx.room.RoomDatabase
import com.valkaryne.appnews.NUMBER_OF_THREADS
import com.valkaryne.appnews.repository.model.NewsEntity
import com.valkaryne.appnews.repository.room.paging.NewsRoomDataSourceFactory
import java.util.concurrent.Executors

/**
 * Part of repository responsible for fetching data from local database
 *
 * @author Valentine Litvin
 */
@Database(entities = [NewsEntity::class], version = 1)
abstract class NewsDatabase : RoomDatabase() {
    val newsPaged: LiveData<PagedList<NewsEntity>>

    abstract fun newsDao(): NewsDao

    init {
        val pagedListConfig = PagedList.Config.Builder().setEnablePlaceholders(false)
            .setInitialLoadSizeHint(Int.MAX_VALUE).setPageSize(Int.MAX_VALUE).build()
        val executor = Executors.newFixedThreadPool(NUMBER_OF_THREADS)
        val dataSourceFactory = NewsRoomDataSourceFactory(newsDao())
        val livePagedListBuilder = LivePagedListBuilder(dataSourceFactory, pagedListConfig)
        newsPaged = livePagedListBuilder.setFetchExecutor(executor).build()
    }
}