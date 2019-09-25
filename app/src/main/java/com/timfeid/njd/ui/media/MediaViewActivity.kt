package com.timfeid.njd.ui.media

import androidx.appcompat.app.AppCompatActivity
import android.webkit.WebSettings
import android.webkit.WebView
import android.os.Bundle
import android.util.Log
import com.timfeid.njd.R
import com.timfeid.njd.api.media.Doc
import kotlinx.android.synthetic.main.activity_media_view.*




class MediaViewActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_media_view)

        val doc = intent.extras!!["news"] as Doc

        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true)

        web_view.loadData(doc.body, "text/html; charset=utf-8", "UTF-8")
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()

        return true
    }
}