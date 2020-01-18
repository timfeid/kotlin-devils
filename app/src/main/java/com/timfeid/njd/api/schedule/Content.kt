package com.timfeid.njd.api.schedule


import android.os.Parcelable
import com.timfeid.njd.api.content.Item
import com.timfeid.njd.api.content.Keyword
import kotlinx.android.parcel.Parcelize
import kotlinx.serialization.Serializable

@Serializable
@Parcelize
data class Content(
    val editorial: Editorial? = null,
    val highlights: Highlights = Highlights(),
    val link: String,
    val media: Media? = null
) : Parcelable {

    fun findHightlightFor(eventId: Int): Item? {
        for (highlight in highlights.scoreboard.items) {
            for (keyword in highlight.keywords) {
                if (keyword.type == Keyword.TYPE_EVENT_ID && keyword.value == eventId.toString()) {
                    return highlight
                }
            }
        }

        return null
    }
}