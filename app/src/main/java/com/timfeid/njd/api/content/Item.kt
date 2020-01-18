package com.timfeid.njd.api.content


import android.os.Parcelable
import android.util.ArrayMap
import kotlinx.android.parcel.Parcelize
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.Optional

@Serializable
@Parcelize
data class Item(
    val approval: String = "",
    val commenting: Boolean = false,
    val contributor: Contributor = Contributor(),
    val dataURI: String = "",
    val date: String = "",
    val headline: String = "",
    val id: String = "",
    val keywords: List<Keyword> = listOf(),
    val keywordsDisplay: List<KeywordsDisplay> = listOf(),
    val media: Media = Media(),
    val preview: String = "",
    val primaryKeyword: PrimaryKeyword = PrimaryKeyword(),
    val seoDescription: String = "",
    val seoKeywords: String = "",
    val seoTitle: String = "",
    val slug: String = "",
    val state: String = "",
    val subhead: String = "",
    val tagline: String = "",
    val tokenData: Map<String, TokenData> = ArrayMap<String, TokenData>(),
    val type: String = "",
    val url: String = "",
    val playbacks: List<Playback> = listOf()
) : Parcelable {
    fun getMobileUrl(): String? {
        for (playback in playbacks) {
            if (playback.name == Playback.MOBILE) {
                return playback.url
            }
        }

        return null
    }
}