package com.timfeid.njd.api.live


import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
@Parcelize
data class Division(
    val abbreviation: String = "",
    val id: Int = 0,
    val link: String = "",
    val name: String = "",
    val nameShort: String = ""
) : Parcelable