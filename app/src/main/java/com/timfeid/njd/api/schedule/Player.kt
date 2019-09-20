package com.timfeid.njd.api.schedule


import com.timfeid.njd.BuildConfig
import com.timfeid.njd.api.live.SkaterStats
import kotlinx.serialization.Serializable

@Serializable
data class Player(
    val jerseyNumber: String? = null,
    val person: Person,
    val position: Position,
    val stats: com.timfeid.njd.api.live.Stats = com.timfeid.njd.api.live.Stats()
) {
    fun findCurrentStats(): Stats {
        for (stat in person.stats!!) {
            if (stat.type.displayName == "statsSingleSeason") {
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