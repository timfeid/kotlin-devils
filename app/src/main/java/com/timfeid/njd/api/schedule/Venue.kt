package com.timfeid.njd.api.schedule


import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import kotlinx.serialization.Serializable

@Serializable
@Parcelize
data class Venue(
    val city: String? = null,
    val id: Int? = null,
    val link: String,
    val name: String,
    val timeZone: TimeZone = TimeZone()
) : Parcelable