package com.valkaryne.appnews.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.valkaryne.appnews.repository.model.NewsEntity

class NewsDetailsViewModel : ViewModel() {
    private val news = MutableLiveData<NewsEntity>()

    fun postEntity(entity: NewsEntity) {
        news.postValue(entity)
    }

    fun getEntity() = news.value

    fun entityHasActiveObservers() = news.hasActiveObservers()
}