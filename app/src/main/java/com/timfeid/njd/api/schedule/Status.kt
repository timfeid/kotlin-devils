package com.timfeid.njd.api.schedule


import android.os.Parcelable
import android.util.Log
import kotlinx.android.parcel.Parcelize
import kotlinx.serialization.Serializable

@Serializable
@Parcelize
data class Status(
    val abstractGameState: String,
    val codedGameState: String,
    val detailedState: String,
    val startTimeTBD: Boolean,
    val statusCode: String
) : Parcelable {
    companion object {
        val GAME_STATUS_FINAL = "7"
        val GAME_STATUS_SCHEDULED = "1"
        val GAME_STATUS_POSTPONED = "9"
    }

    fun isFinal (): Boolean {
        if (statusCode == GAME_STATUS_FINAL) {
            return true
        }

        return false
    }

    fun isScheduled(): Boolean {
        if (statusCode == GAME_STATUS_SCHEDULED || isPostponed()) {
            return true
        }

        return false
    }

    fun isPostponed(): Boolean {
        if (statusCode == GAME_STATUS_POSTPONED) {
            return true
        }

        return false
    }

    fun isLive(): Boolean {
        Log.d("m3", isFinal().toString() + " ? " + isScheduled().toString())
        return !isFinal() && !isScheduled()
    }
}