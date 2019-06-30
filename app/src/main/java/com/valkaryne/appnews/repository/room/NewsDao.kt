package com.valkaryne.appnews.repository.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.valkaryne.appnews.repository.model.NewsEntity

@Dao
interface NewsDao {
    @Query("SELECT * FROM news")
    fun getNews(): List<NewsEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertNews(newsEntity: NewsEntity)
}