package com.timfeid.njd.api.schedule


import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import kotlinx.serialization.Serializable
import java.text.SimpleDateFormat
import java.util.Date

@Serializable
@Parcelize
data class Person(
    val active: Boolean = false,
    val alternateCaptain: Boolean = false,
    val birthCity: String = "",
    val birthCountry: String = "",
    val birthStateProvince: String = "",
    val birthDate: String = "",
    val captain: Boolean = false,
    val currentAge: Int = 0,
    val currentTeam: CurrentTeam = CurrentTeam(),
    val firstName: String = "",
    val fullName: String,
    val primaryNumber: String = "",
    val height: String = "",
    val id: Int,
    val lastName: String = "",
    val shootsCatches: String = "",
    val link: String,
    val nationality: String = "",
    val otherNames: OtherNames = OtherNames(),
    val primaryPosition: PrimaryPosition = PrimaryPosition(),
    val rookie: Boolean = false,
    val rosterStatus: String = "",
    val stats: List<Stat> = listOf(),
    val draft: List<Draft> = listOf(),
    val weight: Int = 0
) : Parcelable {
    fun shortName (): String {
        val firstInitial = fullName.substring(0, 1)

        val lastName = if (lastName.isEmpty()) { fullName.substring(fullName.indexOf(' ')+1) } else {lastName}

        return "$firstInitial. $lastName"
    }

    fun getImageUrl (): String {
        return "https://nhl.bamcontent.com/images/headshots/current/60x60/$id.png"
    }

    fun getActionImageUrl () : String {
        return "https://nhl.bamcontent.com/images/actionshots/$id.jpg"
    }

    fun birthdate (): Date {
        val parser = SimpleDateFormat("yyyy-MM-dd")

        return parser.parse(birthDate)
    }
}