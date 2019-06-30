package com.valkaryne.appnews.ui.listeners

import com.valkaryne.appnews.repository.model.NewsEntity

interface ItemClickListener {
    fun onItemClick(newsEntity: NewsEntity)
}