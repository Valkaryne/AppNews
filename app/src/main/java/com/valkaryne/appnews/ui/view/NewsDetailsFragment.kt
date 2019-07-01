package com.valkaryne.appnews.ui.view

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.valkaryne.appnews.R
import com.valkaryne.appnews.repository.model.NewsEntity
import com.valkaryne.appnews.ui.viewmodel.NewsDetailsViewModel
import com.valkaryne.appnews.utils.DATE_PATTERN
import com.valkaryne.appnews.utils.DATE_PATTERN_PUBLISH
import com.valkaryne.appnews.utils.convertToString
import com.valkaryne.appnews.utils.convertToTimestamp
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class NewsDetailsFragment : Fragment() {
    private val viewModel: NewsDetailsViewModel by sharedViewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_news_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val ivImage = view.findViewById<ImageView>(R.id.iv_image)
        val tvTitle = view.findViewById<TextView>(R.id.tv_title)
        val tvDate = view.findViewById<TextView>(R.id.tv_date)
        val tvDescription = view.findViewById<TextView>(R.id.tv_description)
        val btnFull = view.findViewById<Button>(R.id.btn_full)

        val news = viewModel.getEntity()
        news?.let {
            Glide.with(requireContext())
                .load(it.urlToImage)
                .apply(RequestOptions.fitCenterTransform())
                .into(ivImage)

            tvTitle.text = news.title
            tvDate.text = news.publishedAt.convertToTimestamp(DATE_PATTERN)
                .convertToString(DATE_PATTERN_PUBLISH)
            tvDescription.text = news.description
            btnFull.setOnClickListener { readFullArticle(news) }
        }

        if (news == null) {
            btnFull.visibility = View.GONE
        }
    }

    private fun readFullArticle(news: NewsEntity) {
        startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(news.url)))
    }
}