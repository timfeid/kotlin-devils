package com.timfeid.njd.api.content


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class Editorial(
    val preview: Preview = Preview(),
    val recap: Recap = Recap()
)