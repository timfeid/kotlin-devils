package com.timfeid.njd.api.content


import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
@Parcelize
data class Playback(
    val height: String? = null,
    val name: String = "",
    val url: String = "",
    val width: String? = null
) : Parcelable {
    companion object {
        val MOBILE = "HTTP_CLOUD_MOBILE"
    }
}