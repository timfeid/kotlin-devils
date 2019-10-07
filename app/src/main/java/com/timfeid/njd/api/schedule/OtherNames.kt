package com.timfeid.njd.api.schedule


import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import kotlinx.serialization.Serializable

@Serializable
@Parcelize
data class OtherNames(
    val firstLastNameRoman: String = "",
    val lastFirstName: String = "",
    val slug: String = ""
) : Parcelable