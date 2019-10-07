package com.timfeid.njd.api.schedule


import android.os.Parcelable
import com.timfeid.njd.BuildConfig
import com.timfeid.njd.api.live.SkaterStats
import kotlinx.android.parcel.Parcelize
import kotlinx.serialization.Serializable

@Serializable
@Parcelize
data class Player(
    val jerseyNumber: String? = null,
    val person: Person,
    val position: Position,
    val stats: com.timfeid.njd.api.live.Stats = com.timfeid.njd.api.live.Stats(),
    val id: Int = 0
) : Parcelable {
    fun findCurrentStats(): Stats {
        for (stat in person.stats!!) {
            if (stat.type.displayName == "statsSingleSeason" || stat.type.displayName == "yearByYear") {
                if (stat.splits != null) {
                    for (split in stat.splits) {
                        if (split.season == BuildConfig.API_SEASON) {
                            return split.stat
                        }
                    }
                }
            }
        }

        return Stats()
    }
}