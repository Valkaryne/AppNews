package com.valkaryne.appnews.repository.model

import androidx.recyclerview.widget.DiffUtil

data class NewsEntity(
    val title: String,
    val urlToImage: String,
    val publishedAt: String,
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

