package com.timfeid.njd.api.content


import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.Optional

@Serializable
@Parcelize
data class KeywordsDisplay(
    val displayName: String = "",
    val type: String = "",
    val value: String = ""
) : Parcelable