package com.valkaryne.appnews.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.valkaryne.appnews.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragments_container, NewsListFragment())
            .commit()
    }
}
