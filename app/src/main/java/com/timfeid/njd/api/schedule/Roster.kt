package com.timfeid.njd.api.schedule


import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import kotlinx.serialization.Serializable

@Serializable
@Parcelize
data class Roster(
    val link: String = "",
    val roster: List<Player> = listOf()
) : Parcelable