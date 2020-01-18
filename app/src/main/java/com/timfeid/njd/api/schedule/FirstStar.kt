package com.timfeid.njd.api.schedule


import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import kotlinx.serialization.Serializable

@Serializable
@Parcelize
data class FirstStar(
    val fullName: String,
    val id: Int,
    val link: String
) : Parcelable