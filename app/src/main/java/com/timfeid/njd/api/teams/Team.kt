package com.timfeid.njd.api.teams

import kotlinx.serialization.Serializable

@Serializable
data class Team (
    val franchise: Franchise = Franchise()
) {
}