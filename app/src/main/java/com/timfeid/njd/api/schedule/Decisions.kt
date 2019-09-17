package com.timfeid.njd.api.schedule


import kotlinx.serialization.Serializable

@Serializable
data class Decisions(
    val firstStar: FirstStar? = null,
    val loser: Loser? = null,
    val secondStar: SecondStar? = null,
    val thirdStar: ThirdStar? = null,
    val winner: Winner? = null
)