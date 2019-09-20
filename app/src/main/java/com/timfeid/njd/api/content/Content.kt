package com.timfeid.njd.api.content


import android.util.Log
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.Optional

@Serializable
data class Content(
    val copyright: String = "",
    val editorial: Editorial = Editorial(),
    val highlights: Highlights = Highlights(),
    val link: String = "",
    val media: Media = Media()
) {
    fun findHightlightFor(eventId: Int): Item? {
        for (highlight in highlights.scoreboard.items) {
            for (keyword in highlight.keywords) {
                Log.d(keyword.value, eventId.toString())
                if (keyword.type == Keyword.TYPE_EVENT_ID && keyword.value == eventId.toString()) {
                    return highlight
                }
            }
        }

        return null
    }
}