package com.valkaryne.appnews.ui.listeners

import com.valkaryne.appnews.repository.model.NewsEntity

/**
 * Listener for performing onClick events in RecyclerView
 *
 * @author Valentine Litvin
 */
interface ItemClickListener {
    fun onItemClick(newsEntity: NewsEntity)
}