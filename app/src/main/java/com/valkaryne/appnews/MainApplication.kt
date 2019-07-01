package com.valkaryne.appnews

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

/**
 * [Application] class responsible for Koin module definition
 *
 * @author Valentine Litvin
 */
class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@MainApplication)
            modules(mainAppModule)
        }
    }
}