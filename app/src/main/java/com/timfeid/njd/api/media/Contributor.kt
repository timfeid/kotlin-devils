package com.timfeid.njd.api.media


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class Contributor(
    val contributors: List<ContributorPerson> = listOf(),
    val source: String = ""
) : java.io.Serializable