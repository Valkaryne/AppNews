package com.valkaryne.appnews.repository.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.valkaryne.appnews.repository.model.NewsEntity

/**
 * Data access object for news entities
 *
 * @author Valentine Litvin
 */
@Dao
interface NewsDao {
    /**
     * Get the News from the database
     *
     * @return the news from the database
     */
    @Query("SELECT * FROM news")
    fun getNews(): List<NewsEntity>

    /**
     * Insert a news into the database. If the news already exists, ignore insertion
     *
     * @param newsEntity the news to be inserted
     */
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertNews(newsEntity: NewsEntity)
}