package com.timfeid.njd.api.response


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.Optional

@Serializable
data class Decisions(
    val firstStar: FirstStar? = null,
    val loser: Loser? = null,
    val secondStar: SecondStar? = null,
    val thirdStar: ThirdStar? = null,
    val winner: Winner? = null
)