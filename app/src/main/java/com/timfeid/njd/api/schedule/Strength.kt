package com.timfeid.njd.api.schedule


import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import kotlinx.serialization.Serializable

@Serializable
@Parcelize
data class Strength(
    val code: String,
    val name: String
) : Parcelable {
    companion object {
        val CODE_EVEN = "EVEN"
    }
}