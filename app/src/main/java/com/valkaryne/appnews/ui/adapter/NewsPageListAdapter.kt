package com.valkaryne.appnews.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.valkaryne.appnews.R
import com.valkaryne.appnews.repository.model.NewsEntity
import com.valkaryne.appnews.ui.listeners.ItemClickListener
import com.valkaryne.appnews.utils.DATE_PATTERN
import com.valkaryne.appnews.utils.DATE_PATTERN_PUBLISH
import com.valkaryne.appnews.utils.convertToString
import com.valkaryne.appnews.utils.convertToTimestamp

class NewsPageListAdapter(private val itemClickListener: ItemClickListener) :
    PagedListAdapter<NewsEntity, NewsPageListAdapter.ViewHolder>(NewsEntity.DIFF_CALLBACK) {

    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        val itemView = LayoutInflater.from(context)
            .inflate(R.layout.item_news, parent, false) as CardView
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val news = getItem(position)
        val itemView = holder.item

        val tvTitle = itemView.findViewById<TextView>(R.id.tv_item_title)
        val ivImage = itemView.findViewById<ImageView>(R.id.iv_item_image)
        val tvDate = itemView.findViewById<TextView>(R.id.tv_item_date)

        Glide.with(itemView)
            .load(news!!.urlToImage)
            .apply(RequestOptions.centerCropTransform())
            .into(ivImage)

        tvTitle.text = news.title
        tvDate.text = news.publishedAt.convertToTimestamp(DATE_PATTERN)
            .convertToString(DATE_PATTERN_PUBLISH)

        itemView.setOnClickListener { itemClickListener.onItemClick(news) }
    }

    inner class ViewHolder(val item: CardView) : RecyclerView.ViewHolder(item)
}