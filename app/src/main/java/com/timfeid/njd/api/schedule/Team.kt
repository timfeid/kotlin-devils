package com.timfeid.njd.api.schedule


import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.Optional

@Serializable
@Parcelize
data class Team(
    val id: Int = 0,
    val link: String = "",
    val name: String = ""
) : Parcelable