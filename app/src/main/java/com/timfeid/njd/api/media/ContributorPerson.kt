package com.timfeid.njd.api.media


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class ContributorPerson(
    val name: String = "",
    val twitter: String = ""
) : java.io.Serializable