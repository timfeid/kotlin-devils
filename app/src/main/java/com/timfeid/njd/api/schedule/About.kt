package com.timfeid.njd.api.schedule


import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import kotlinx.serialization.Serializable

@Serializable
@Parcelize
data class About(
    val dateTime: String,
    val eventId: Int,
    val eventIdx: Int,
    val goals: Goals,
    val ordinalNum: String,
    val period: Int,
    val periodTime: String,
    val periodTimeRemaining: String,
    val periodType: String
) : Parcelable