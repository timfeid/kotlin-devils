package com.timfeid.njd.api.schedule


import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import kotlinx.serialization.Serializable

@Serializable
@Parcelize
data class Decisions(
    val firstStar: FirstStar? = null,
    val loser: Loser? = null,
    val secondStar: FirstStar? = null,
    val thirdStar: FirstStar? = null,
    val winner: FirstStar? = null
) : Parcelable