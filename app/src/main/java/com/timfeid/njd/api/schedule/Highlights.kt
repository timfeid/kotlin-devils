package com.timfeid.njd.api.schedule


import android.os.Parcelable
import com.timfeid.njd.api.content.GameCenter
import com.timfeid.njd.api.content.Scoreboard
import kotlinx.android.parcel.Parcelize
import kotlinx.serialization.Serializable

@Serializable
@Parcelize
data class Highlights(
    val gameCenter: GameCenter = GameCenter(),
    val scoreboard: Scoreboard = Scoreboard()
) : Parcelable