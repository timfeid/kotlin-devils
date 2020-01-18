package com.timfeid.njd.api.schedule


import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import kotlinx.serialization.Serializable

@Serializable
@Parcelize
data class Result(
    val description: String,
    val emptyNet: Boolean?=null,
    val event: String,
    val eventCode: String,
    val eventTypeId: String,
    val gameWinningGoal: Boolean? = null,
    val secondaryType: String?= null,
    val strength: Strength?=null
) : Parcelable