package com.timfeid.njd.api.live

import kotlinx.serialization.Serializable

@Serializable
data class ShootoutScoresAttempts (
    val scores: Int,
    val attempts: Int
)
