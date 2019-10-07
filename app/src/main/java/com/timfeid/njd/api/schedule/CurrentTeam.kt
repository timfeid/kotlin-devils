package com.timfeid.njd.api.schedule


import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import kotlinx.serialization.Serializable

@Serializable
@Parcelize
data class CurrentTeam(
    val id: Int = 0,
    val link: String = "",
    val name: String = ""
) : Parcelable