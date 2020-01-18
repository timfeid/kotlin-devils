package com.timfeid.njd.api.schedule


import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import kotlinx.serialization.Serializable

@Serializable
@Parcelize
data class Broadcast(
    val id: Int,
    val name: String,
    val type: String,
    val language: String
) : Parcelable