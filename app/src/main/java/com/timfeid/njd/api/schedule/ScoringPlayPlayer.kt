package com.timfeid.njd.api.schedule


import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import kotlinx.serialization.Serializable

@Serializable
@Parcelize
data class ScoringPlayPlayer(
    val player: Person,
    val playerType: String,
    val seasonTotal: Int? = null
) : Parcelable