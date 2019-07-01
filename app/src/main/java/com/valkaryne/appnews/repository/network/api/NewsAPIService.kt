package com.valkaryne.appnews.repository.network.api

import com.valkaryne.appnews.repository.model.NewsAPIResponse
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Interface responsible for calls to API
 *
 * @author Valentine Litvin
 */
interface NewsAPIService {

    /**
     * Method calling to the API
     *
     * @param apiKey key to get access to the API
     * @param page number of page to return
     * @param country country news you want to get from
     * @return response from the API
     */
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

