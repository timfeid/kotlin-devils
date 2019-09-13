package com.timfeid.njd.api.response


import kotlinx.android.parcel.Parcelize
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.Optional

@Serializable
data class Division(
    val abbreviation: String,
    val id: Int,
    val link: String,
    val name: String,
    val nameShort: String
)