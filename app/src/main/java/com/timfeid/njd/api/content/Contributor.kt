package com.timfeid.njd.api.content


import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
@Parcelize
data class Contributor(
    val contributors: List<Contributor> = listOf(),
    val source: String = ""
) : Parcelable