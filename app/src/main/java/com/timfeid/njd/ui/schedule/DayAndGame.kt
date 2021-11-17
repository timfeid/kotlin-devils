package com.timfeid.njd.ui.schedule

import com.timfeid.njd.api.schedule.Game

class DayAndGame {
    val day: String
    val game: Game?

    constructor(day: String, game: Game? = null) {

        this.day = day
        this.game = game
    }
}