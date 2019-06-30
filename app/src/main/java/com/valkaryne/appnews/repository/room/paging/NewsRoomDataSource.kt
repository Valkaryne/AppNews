package com.valkaryne.appnews.repository.room.paging

import androidx.paging.PageKeyedDataSource
import com.valkaryne.appnews.repository.model.NewsEntity
import com.valkaryne.appnews.repository.room.NewsDao

class NewsRoomDataSource(private val dao: NewsDao) : PageKeyedDataSource<String, NewsEntity>() {

    override fun loadInitial(
        params: LoadInitialParams<String>,
        callback: LoadInitialCallback<String, NewsEntity>
    ) {
        val news = dao.getNews()
        if (news.isNotEmpty()) {
            callback.onResult(news, "0", "1")
        }
    }

    override fun loadAfter(
        params: LoadParams<String>,
        callback: LoadCallback<String, NewsEntity>
    ) {
    }

    override fun loadBefore(
        params: LoadParams<String>,
        callback: LoadCallback<String, NewsEntity>
    ) {
    }
}