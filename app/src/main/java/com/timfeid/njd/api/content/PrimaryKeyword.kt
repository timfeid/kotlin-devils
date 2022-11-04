package com.timfeid.njd.api.content


import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
@Parcelize
data class PrimaryKeyword(
    val displayName: String = "",
    val type: String = "",
    val value: String = ""
) : Parcelable