package com.timfeid.njd.api.schedule


import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import kotlinx.serialization.Serializable

@Serializable
@Parcelize
data class ShootoutInfo(
    val away: ShootoutInfoTeam,
    val home: ShootoutInfoTeam
) : Parcelable