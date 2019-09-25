package com.timfeid.njd.api.media


import android.os.Parcelable
import com.timfeid.njd.api.content.Image
import kotlinx.android.parcel.Parcelize
import kotlinx.serialization.Serializable

@Serializable
@Parcelize
data class Media(
    val image: Image = Image(),
    val type: String = ""
) : Parcelable