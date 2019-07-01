package com.valkaryne.appnews.ui.view

import android.content.res.Configuration
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.valkaryne.appnews.R

class MainActivity : AppCompatActivity() {

    private val isPortrait
        get() = resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (isPortrait) {
            setUpPortraitView()
        } else {
            setUpLandView()
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        if (!isPortrait) {
            finish()
        }
    }

    private fun setUpPortraitView() {
        val fragmentDetails = supportFragmentManager.findFragmentByTag(FRDETAILS_TAG) as? NewsDetailsFragment
        supportFragmentManager.popBackStack()
        if (fragmentDetails != null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragments_container, NewsDetailsFragment(), FRDETAILS_TAG)
                .addToBackStack(null)
                .commit()
        } else {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragments_container, NewsListFragment())
                .commit()
        }
    }

    private fun setUpLandView() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragments_container_list, NewsListFragment())
            .commit()
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragments_container_details, NewsDetailsFragment(), FRDETAILS_TAG)
            .commit()
    }

    private companion object {
        const val FRDETAILS_TAG = "FrDetails"
    }
}
