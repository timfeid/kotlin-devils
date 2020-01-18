package com.timfeid.njd.api.schedule


import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import kotlinx.serialization.Serializable

@Serializable
@Parcelize
data class PowerPlayInfo(
    val inSituation: Boolean,
    val situationTimeElapsed: Int,
    val situationTimeRemaining: Int
) : Parcelable