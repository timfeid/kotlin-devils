package com.timfeid.njd.api.live


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.Optional

@Serializable
data class Live(
    val copyright: String,
    val gameData: GameData,
    val gamePk: Int,
    val link: String,
    val liveData: LiveData,
    val metaData: MetaData
)