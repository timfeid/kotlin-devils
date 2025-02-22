package com.timfeid.njd.api.media


import android.os.Parcelable
import android.util.Log
import com.timfeid.njd.UrlMaker
import com.timfeid.njd.api.content.Image
import com.timfeid.njd.api.content.Keyword
import kotlinx.android.parcel.IgnoredOnParcel
import kotlinx.android.parcel.Parcelize
import kotlinx.coroutines.*
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonConfiguration
import java.net.URL

@Serializable
@Parcelize
data class Doc(
    val approval: String = "",
    val body: String = "",
    val commenting: Boolean? = false,
    val contributor: Contributor = Contributor(),
    val dataURI: String = "",
    val date: String = "",
    val headline: String = "",
    val id: String? = "",
    @SerialName("asset_id")
    val assetId: String? = "",
    val keywordsAll: List<Keyword> = listOf(),
    val media: Media = Media(),
    val image: Image = Image(),
    val preview: String = "",
    val primaryKeyword: PrimaryKeyword = PrimaryKeyword(),
    val seoDescription: String? = "",
    val seoKeywords: String? = "",
    val seoTitle: String? = "",
    val slug: String = "",
    val state: String = "",
    val subhead: String? = "",
    val description: String? = "",
    val tagline: String? = "",
    val type: String = "",
    val url: String = ""
) : Parcelable {
    @IgnoredOnParcel
    val bestBlurb: String = when {
        !subhead.isNullOrEmpty() -> subhead
        !description.isNullOrEmpty() -> description
        else -> ""
    }

    @IgnoredOnParcel
    val bestImage: String? = when {
        image.cuts.isNotEmpty() -> image.cuts.values.first().src
        media.image.cuts.isNotEmpty() -> media.image.cuts.values.first().src
        else -> null
    }

    fun reload(): Deferred<Doc> {
        return CoroutineScope(Dispatchers.IO).async{
            fetchAsset()
        }
    }

    private fun fetchAsset(): Doc {
        val url = UrlMaker("web-v1.json")
        url.baseUrl = "https://cms.nhl.bamgrid.com/nhl/id/v1/$assetId/details/"
        val json = Json { ignoreUnknownKeys = true }

//        val json = Json()
        Log.d("raw", url.get())

        val raw = URL(url.get()).readText()

        val parsed = json.decodeFromString(serializer(), raw)

        return parsed
    }

}