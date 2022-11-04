package com.timfeid.njd.api.live


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
class Stats(
    val skaterStats: SkaterStats = SkaterStats(),
    val goalieStats: GoalieStats = GoalieStats()
) : java.io.Serializable