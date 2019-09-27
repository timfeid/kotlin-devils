package com.timfeid.njd.ui.media

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.JavascriptInterface
import com.timfeid.njd.R
import com.timfeid.njd.api.media.Doc
import kotlinx.android.synthetic.main.activity_media_view.*








class MediaViewActivity : AppCompatActivity() {

    var doc: Doc? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_media_view)

        doc = intent.extras!!["news"] as Doc

        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.title = doc!!.headline

        val webSettings = web_view.settings
        webSettings.javaScriptEnabled = true
        web_view.addJavascriptInterface(WebConnector(doc!!), "webConnector")
        web_view.loadUrl("file:///android_asset/njd-helper/index.html")
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()

        return true
    }

    internal class WebConnector(val doc: Doc) {
        @JavascriptInterface
        fun newsId(): String {
            return doc.id!!
        }
    }


}