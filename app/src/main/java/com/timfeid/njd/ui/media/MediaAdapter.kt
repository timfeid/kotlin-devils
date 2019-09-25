package com.timfeid.njd.ui.media

import android.util.Log
import android.widget.LinearLayout
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import android.widget.Button
import android.widget.ImageView
import androidx.fragment.app.FragmentManager
import com.squareup.picasso.Picasso
import com.timfeid.njd.R
import com.timfeid.njd.UrlMaker
import com.timfeid.njd.api.media.Doc
import com.timfeid.njd.api.media.News
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonConfiguration
import java.net.URL


open class MediaAdapter(protected var fragmentManager: FragmentManager, val topicId: String) :
    RecyclerView.Adapter<MediaAdapter.ViewHolder>() {

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

    var mediaDataset = mutableListOf<Doc>()

    inner class ViewHolder(v: LinearLayout) : RecyclerView.ViewHolder(v) {
        var title: TextView
        var blurb: TextView
        var card: CardView
        var image: ImageView
        var button: Button

        init {
            title = v.findViewById(R.id.title)
            image = v.findViewById(R.id.image)
            blurb = v.findViewById(R.id.blurb)
            button = v.findViewById(R.id.button)
            card = v.findViewById(R.id.card_view)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MediaAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.media_row, parent, false) as LinearLayout

        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        var item = mediaDataset[position]

        if (item.id.isNullOrBlank()) {
            CoroutineScope(Dispatchers.Main).launch {
                item.reload().let {
                    item = it.await()
                    mediaDataset[position] = item
                    updateHolder(holder, item)
                }
            }
        }


    }

    private fun updateHolder(holder: ViewHolder, item: Doc) {
        holder.title.text = item.headline
        val image: String? = when {
            item.image.cuts.isNotEmpty() -> item.image.cuts.values.first().src
            item.media.image.cuts.isNotEmpty() -> item.media.image.cuts.values.first().src
            else -> null
        }

        if (image != null) {
            Picasso.get().load(image).into(holder.image)
        }

        holder.blurb.text = when {
            item.subhead!!.isNotBlank() -> item.subhead
            item.description!!.isNotBlank() -> item.description
            else -> "hermmm"
        }
//        holder.button.setOnClickListener(item.getListener(activity))
//        if (position == 0) {
//            val r = activity.applicationContext.resources
//            val px = TypedValue.applyDimension(
//                TypedValue.COMPLEX_UNIT_DIP,
//                16f,
//                r.displayMetrics
//            ).toInt()
//            val params = holder.card.layoutParams as LinearLayout.LayoutParams
//            params.setMargins(px, px, px, px / 2)
//        }
    }

    override fun getItemCount(): Int {
        return mediaDataset.count()

    }
}