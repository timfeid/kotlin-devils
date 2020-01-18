package com.timfeid.njd.api.schedule


import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import kotlinx.serialization.Serializable

@Serializable
@Parcelize
data class Franchise(
    val franchiseId: Int,
    val link: String,
    val teamName: String
) : Parcelable