package com.valkaryne.appnews.repository.model

import com.google.gson.annotations.SerializedName

/**
 * Data class describing response from NewsAPI
 *
 * @author Valentine Litvin
 */
data class NewsAPIResponse(
    val status: String,
    val totalResults: Int,
    @SerializedName("articles") val newsEntities: ArrayList<NewsEntity>
)