package com.timfeid.njd.api.schedule


import kotlinx.serialization.Serializable

@Serializable
data class Strength(
    val code: String,
    val name: String
) {
    companion object {
        val CODE_EVEN = "EVEN"
    }
}