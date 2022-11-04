package com.timfeid.njd.ui.media

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.webkit.JavascriptInterface
import androidx.core.content.ContextCompat.startActivity
import com.timfeid.njd.BaseWebConnector
import com.timfeid.njd.R
import com.timfeid.njd.api.content.Item
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
        web_view.addJavascriptInterface(WebConnector(doc!!, applicationContext), "webConnector")
        web_view.loadUrl("file:///android_asset/njd-helper/index.html")
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()

        return true
    }

    internal class WebConnector(val doc: Doc, val context: Context) : BaseWebConnector() {
        @JavascriptInterface
        override fun component(): String {
            return "news"
        }

        @JavascriptInterface
        fun newsId(): String {
            return doc.id!!
        }

        @JavascriptInterface
        fun viewVideo(url: String) {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            intent.setDataAndType(Uri.parse(url), "video/*")
            context.startActivity(intent)
        }
    }


}