package com.valkaryne.appnews.repository.network.paging

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.valkaryne.appnews.API_KEY
import com.valkaryne.appnews.repository.model.NetworkState
import com.valkaryne.appnews.repository.model.NewsAPIResponse
import com.valkaryne.appnews.repository.model.NewsEntity
import com.valkaryne.appnews.repository.network.api.NewsAPIService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Data Source for news fetched from network.
 *
 * @author Valentine Litvin
 */
class NewsNetworkDataSource : PageKeyedDataSource<String, NewsEntity>() {

    private val newsService = NewsAPIService.retrofit.create(NewsAPIService::class.java)
    private val newsData = MutableLiveData<List<NewsEntity>>()
    private val networkState = MutableLiveData<NetworkState>()

    /**
     * @return immutable network state LiveData
     */
    fun getNetworkState(): LiveData<NetworkState> = networkState

    /**
     * @return immutable news list LiveData
     */
    fun getNews(): LiveData<List<NewsEntity>> = newsData

    override fun loadInitial(
        params: LoadInitialParams<String>,
        loadCallback: LoadInitialCallback<String, NewsEntity>
    ) {
        networkState.postValue(NetworkState.LOADING)
        val callback = newsService.getNews(API_KEY, 1)
        callback.enqueue(object : Callback<NewsAPIResponse> {
            override fun onResponse(
                call: Call<NewsAPIResponse>,
                response: Response<NewsAPIResponse>
            ) {
                if (response.isSuccessful) {
                    loadCallback.onResult(response.body()!!.newsEntities.toMutableList(), "1", "2")
                    networkState.postValue(NetworkState.SUCCESS)
                    newsData.postValue(response.body()!!.newsEntities)
                } else {
                    networkState.postValue(
                        NetworkState(
                            NetworkState.Status.FAILED,
                            response.message()
                        )
                    )
                }
            }

            override fun onFailure(call: Call<NewsAPIResponse>, t: Throwable) {
                val errorMessage = if (t.message == null) "unknown error" else t.message
                networkState.postValue(NetworkState(NetworkState.Status.FAILED, errorMessage!!))
                loadCallback.onResult(ArrayList<NewsEntity>(), "1", "2")
            }
        })
    }

    override fun loadAfter(
        params: LoadParams<String>,
        loadCallback: LoadCallback<String, NewsEntity>
    ) {
        //Log.d("SuperCat", "Load After")
        networkState.postValue(NetworkState.LOADING)

        val page = params.key.toInt()

        val callback = newsService.getNews(API_KEY, page)
        callback.enqueue(object : Callback<NewsAPIResponse> {
            override fun onResponse(
                call: Call<NewsAPIResponse>,
                response: Response<NewsAPIResponse>
            ) {
                if (response.isSuccessful) {
                    loadCallback.onResult(
                        response.body()!!.newsEntities.toMutableList(),
                        (page + 1).toString()
                    )
                    networkState.postValue(NetworkState.SUCCESS)
                    newsData.postValue(response.body()!!.newsEntities)
                } else {
                    networkState.postValue(
                        NetworkState(
                            NetworkState.Status.FAILED,
                            response.message()
                        )
                    )
                }
            }

            override fun onFailure(call: Call<NewsAPIResponse>, t: Throwable) {
                val errorMessage = if (t.message == null) "unknown error" else t.message
                networkState.postValue(NetworkState(NetworkState.Status.FAILED, errorMessage!!))
                loadCallback.onResult(ArrayList<NewsEntity>(), page.toString())
            }
        })
    }

    override fun loadBefore(
        params: LoadParams<String>,
        callback: LoadCallback<String, NewsEntity>
    ) {

    }
}