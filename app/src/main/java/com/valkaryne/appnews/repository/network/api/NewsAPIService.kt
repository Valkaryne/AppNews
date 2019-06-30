package com.valkaryne.appnews.repository.network.api

import com.valkaryne.appnews.repository.model.NewsAPIResponse
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsAPIService {

    @GET("top-headlines")
    fun getNews(
        @Query("apiKey") apiKey: String,
        @Query("page") page: Int,
        @Query("country") country: String = "us"
    ): Call<NewsAPIResponse>

    companion object {
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("https://newsapi.org/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}

