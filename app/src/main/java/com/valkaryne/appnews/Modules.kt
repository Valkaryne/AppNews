package com.valkaryne.appnews

import androidx.room.Room
import com.valkaryne.appnews.repository.NewsRepository
import com.valkaryne.appnews.repository.room.NewsDatabase
import com.valkaryne.appnews.ui.viewmodel.NewsDetailsViewModel
import com.valkaryne.appnews.ui.viewmodel.NewsListViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * Simple module for Koin
 *
 * @author Valentine Litvin
 */
val mainAppModule = module {
    // Repository
    single {
        Room.databaseBuilder(androidApplication(), NewsDatabase::class.java, "NewsDB").build()
    }

    single { NewsRepository(get()) }

    // ViewModels
    viewModel { NewsListViewModel(get()) }
    viewModel { NewsDetailsViewModel() }
}