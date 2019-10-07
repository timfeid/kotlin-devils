package com.timfeid.njd.api.live


import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.Optional

@Serializable
@Parcelize
data class TimeZone(
    val id: String = "",
    val offset: Int = 0,
    val tz: String = ""
) : Parcelable