package com.valkaryne.appnews.ui.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.paging.PagedList
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.valkaryne.appnews.R
import com.valkaryne.appnews.repository.model.NetworkState
import com.valkaryne.appnews.repository.model.NewsEntity
import com.valkaryne.appnews.ui.adapter.NewsPageListAdapter
import com.valkaryne.appnews.ui.viewmodel.NewsListViewModel

class NewsListFragment : Fragment() {

    private lateinit var listViewModel: NewsListViewModel

    private lateinit var recyclerView: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var layoutRefresh: SwipeRefreshLayout

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_news_list, container, false)
        recyclerView = view.findViewById(R.id.recycler_news)
        progressBar = view.findViewById(R.id.progress_bar)
        layoutRefresh = view.findViewById(R.id.layout_refresh)
        recyclerView.layoutManager = LinearLayoutManager(context)
        listViewModel = ViewModelProviders.of(activity!!).get(NewsListViewModel::class.java)
        registerObservers()
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        layoutRefresh.setOnRefreshListener {
            listViewModel.refresh()
        }
    }

    private fun registerObservers() {
        val pageListAdapter = NewsPageListAdapter()
        listViewModel.networkState.observe(this,
            Observer<NetworkState> {
                switchProgressBarStatus(it)
            })
        listViewModel.news.observe(this,
            Observer<PagedList<NewsEntity>> { list -> pageListAdapter.submitList(list) })
        recyclerView.adapter = pageListAdapter
    }

    private fun switchProgressBarStatus(state: NetworkState) {
        Log.d("SuperCat", "${state.getStatus()}")
        when (state.getStatus()) {
            NetworkState.Status.RUNNING -> layoutRefresh.isRefreshing = true
            NetworkState.Status.SUCCEED -> layoutRefresh.isRefreshing = false
            NetworkState.Status.FAILED -> {
                Toast.makeText(
                    context,
                    state.getMessage(),
                    Toast.LENGTH_LONG
                ).show()
                layoutRefresh.isRefreshing = false
            }
        }
    }
}