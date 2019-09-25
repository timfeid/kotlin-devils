package com.timfeid.njd.ui.media

import android.util.Log
import androidx.fragment.app.FragmentManager
import com.timfeid.njd.UrlMaker
import com.timfeid.njd.api.media.News
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonConfiguration
import java.net.URL


class NewsAdapter(fragmentManager: FragmentManager, val topicId: String) : MediaAdapter(fragmentManager) {
    init {
        CoroutineScope(Dispatchers.IO).launch {
            fetchMedia()
            CoroutineScope(Dispatchers.Main).launch {
                notifyDataSetChanged()
            }
        }
    }

    fun fetchMedia (): News {
        val url = UrlMaker(topicId.toString())
        url.baseUrl = "https://search-api.svc.nhl.com/svc/search/v2/nhl_global_en/topic/"

        val json = Json(JsonConfiguration(strictMode = false))

        Log.d("raw", url.get())

        val raw = URL(url.get()).readText()

        val parsed = json.parse(News.serializer(), raw)

        for (doc in parsed.docs) {
            mediaDataset.add(doc)
        }

        return parsed
    }

}