package com.valkaryne.appnews.repository.model

import androidx.recyclerview.widget.DiffUtil
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Data class describing news entity
 *
 * @author Valentine Litvin
 */
@Entity(tableName = "news")
data class NewsEntity(
    @PrimaryKey val title: String,
    @ColumnInfo(name = "url_to_image") val urlToImage: String,
    @ColumnInfo(name = "published_at") val publishedAt: String,
    val description: String,
    val url: String
) {
    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<NewsEntity>() {
            override fun areItemsTheSame(oldItem: NewsEntity, newItem: NewsEntity): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: NewsEntity, newItem: NewsEntity): Boolean {
                return oldItem.title == newItem.title
            }

        }
    }
}

