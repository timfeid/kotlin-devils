package com.timfeid.njd.api.schedule


import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import kotlinx.serialization.Serializable

@Serializable
@Parcelize
data class PrimaryPosition(
    val abbreviation: String = "",
    val code: String = "",
    val name: String = "",
    val type: String = ""
) : Parcelable