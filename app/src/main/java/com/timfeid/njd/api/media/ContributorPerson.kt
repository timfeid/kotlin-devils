package com.timfeid.njd.api.media


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.Optional

@Serializable
data class ContributorPerson(
    val name: String = "",
    val twitter: String = ""
) : java.io.Serializable