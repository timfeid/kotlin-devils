package com.timfeid.njd.api.schedule


import kotlinx.serialization.Serializable

@Serializable
data class Person(
    val active: Boolean? = null,
    val alternateCaptain: Boolean = false,
    val birthCity: String? = null,
    val birthCountry: String? = null,
    val birthDate: String? = null,
    val captain: Boolean = false,
    val currentAge: Int? = null,
    val currentTeam: CurrentTeam? = null,
    val firstName: String? = null,
    val fullName: String,
    val primaryNumber: String? = null,
    val height: String? = null,
    val id: Int,
    val lastName: String? = null,
    val shootsCatches: String? = null,
    val link: String,
    val nationality: String? = null,
    val otherNames: OtherNames? = null,
    val primaryPosition: PrimaryPosition? = null,
    val rookie: Boolean? = null,
    val rosterStatus: String? = null,
    val stats: List<Stat>? = null,
    val weight: Int? = null
) {
    fun shortName (): String {
        val firstInitial = fullName.substring(0, 1)

        return "$firstInitial. $lastName"
    }
}