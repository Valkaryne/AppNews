package com.valkaryne.appnews.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.valkaryne.appnews.repository.model.NewsEntity

/**
 * [NewsDetailsViewModel] is used as shared communication viewModel between NewsListFragment
 * and NewsDetailsFragment
 *
 * @author Valentine Litvin
 */
class NewsDetailsViewModel : ViewModel() {
    private val news = MutableLiveData<NewsEntity>()

    /**
     * Puts selected news to the ViewModel
     *
     * @param entity a selected news
     */
    fun postEntity(entity: NewsEntity) {
        news.postValue(entity)
    }

    /**
     * @return news containing in the ViewModel
     */
    fun getEntity() = news.value

    /**
     * Checks if the selected news entity has active observers
     *
     * @return true/false answer
     */
    fun entityHasActiveObservers() = news.hasActiveObservers()
}