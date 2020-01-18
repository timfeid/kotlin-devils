package com.timfeid.njd.api.schedule


import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import kotlinx.serialization.Serializable

@Serializable
@Parcelize
data class Linescore(
    val currentPeriod: Int,
    val currentPeriodOrdinal: String? = null,
    val currentPeriodTimeRemaining: String? = null,
    val hasShootout: Boolean,
    val intermissionInfo: IntermissionInfo,
    val periods: List<Period>,
    val powerPlayInfo: PowerPlayInfo? = null,
    val powerPlayStrength: String? = null,
    val shootoutInfo: ShootoutInfo? = null,
    val teams: ScheduleTeams
) : Parcelable