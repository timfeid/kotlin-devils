package com.timfeid.njd.api.response


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.Optional

@Serializable
data class Person(
    val active: Boolean,
    val alternateCaptain: Boolean,
    val birthCity: String,
    val birthCountry: String,
    val birthDate: String,
    val captain: Boolean,
    val currentAge: Int,
    val currentTeam: CurrentTeam,
    val firstName: String,
    val fullName: String,
    val primaryNumber: String? = null,
    val height: String,
    val id: Int,
    val lastName: String,
    val shootsCatches: String? = null,
    val link: String,
    val nationality: String? = null,
    val otherNames: OtherNames,
    val primaryPosition: PrimaryPosition,
    val rookie: Boolean,
    val rosterStatus: String,
    val stats: List<Stat>,
    val weight: Int
)