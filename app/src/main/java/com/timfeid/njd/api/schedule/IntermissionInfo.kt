package com.timfeid.njd.api.schedule


import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import kotlinx.serialization.Serializable

@Serializable
@Parcelize
data class IntermissionInfo(
    val inIntermission: Boolean,
    val intermissionTimeElapsed: Int,
    val intermissionTimeRemaining: Int
) : Parcelable