package com.valkaryne.appnews.repository.model

import com.google.gson.annotations.SerializedName

data class NewsAPIResponse(
    val status: String,
    val totalResults: Int,
    @SerializedName("articles") val newsEntities: ArrayList<NewsEntity>
)