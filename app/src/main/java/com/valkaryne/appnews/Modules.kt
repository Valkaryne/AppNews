package com.valkaryne.appnews

import com.valkaryne.appnews.repository.NewsRepository
import com.valkaryne.appnews.ui.viewmodel.NewsDetailsViewModel
import com.valkaryne.appnews.ui.viewmodel.NewsListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val mainAppModule = module {
    // Repository
    single { NewsRepository() }

    // ViewModels
    viewModel { NewsListViewModel(get()) }
    viewModel { NewsDetailsViewModel() }
}