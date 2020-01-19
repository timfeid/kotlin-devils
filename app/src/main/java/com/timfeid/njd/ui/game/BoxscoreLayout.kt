package com.timfeid.njd.ui.game

import android.view.Gravity
import android.widget.TableLayout
import androidx.core.content.ContextCompat
import android.content.Context
import android.util.Log
import android.view.ViewGroup
import android.widget.TextView
import android.widget.LinearLayout
import androidx.core.view.marginBottom
import androidx.core.view.marginTop
import com.timfeid.njd.R
import com.timfeid.njd.ui.BoldFontTextView
import com.timfeid.njd.ui.FontTextView


class BoxscoreLayout(context: Context, protected val string: String) : LinearLayout(context) {

    internal var stat: TextView
    internal var home: TextView
    internal var away: TextView
    internal var linearLayout: LinearLayout = LinearLayout(context)
    internal var bar: LinearLayout = LinearLayout(context)
    internal var homeBar: LinearLayout = LinearLayout(context)
    internal var awayBar: LinearLayout = LinearLayout(context)
    val white = ContextCompat.getColor(getContext(), R.color.colorAccent)
    val color = ContextCompat.getColor(getContext(), R.color.colorPrimary)
    val transparent = ContextCompat.getColor(getContext(), R.color.transparentIsh)
    val totalWeight = 0

    init {
        orientation = VERTICAL

        home = BoldFontTextView(context, null)
        stat = FontTextView(context, null)
        away = BoldFontTextView(context, null)

        homeBar.layoutParams = LayoutParams(0, 2, 5f)
        awayBar.layoutParams = LayoutParams(0, 2, 3f)
        homeBar.setBackgroundColor(transparent)
        awayBar.setBackgroundColor(white)

        val barParams = LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        barParams.bottomMargin = 8
        bar.addView(homeBar)
        bar.addView(awayBar)
        bar.layoutParams = barParams
        bar.weightSum = 8f

        linearLayout.setPadding(0, 5, 0, 5)
        linearLayout.addView(home)
        linearLayout.addView(stat)
        linearLayout.addView(away)
        linearLayout.weightSum = 3f
        linearLayout.layoutParams = LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)

        addView(linearLayout)
        addView(bar)

        setupView(home)
        setupView(stat)
        setupView(away)

        stat.textSize = 12f
        stat.setTextColor(white)
        stat.gravity = Gravity.CENTER
        away.gravity = Gravity.END

        stat.text = string

    }

    fun setupView (view: TextView) {
        view.textSize = 16f
        view.isAllCaps = true
        view.layoutParams = TableLayout.LayoutParams(
            LayoutParams.WRAP_CONTENT,
            LayoutParams.WRAP_CONTENT,
            1f
        )
        view.setTextColor(white)
    }

    fun setHome(text: String) {
        home.text = text
        resetBars()
    }

    fun setAway(text: String) {
        away.text = text
        resetBars()
    }

    fun teamIsHome (home: Boolean) {

        homeBar.setBackgroundColor(if (home) {
            color
        } else {
            transparent
        })

        awayBar.setBackgroundColor(if (!home) {
            color
        } else {
            transparent
        })
    }

    fun resetBars() {
        val homev = home.text.toString().toFloatOrNull() ?: 0f
        val awayv = away.text.toString().toFloatOrNull() ?: 0f

        bar.weightSum = homev + awayv

        homeBar.layoutParams = LayoutParams(0, 2, homev)
        awayBar.layoutParams = LayoutParams(0, 2, awayv)

        home.setTextColor(if (homev >= awayv) { white } else { transparent })
        away.setTextColor(if (homev < awayv) { white } else { transparent })
    }
}
