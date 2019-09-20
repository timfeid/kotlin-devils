package com.timfeid.njd.ui.game

import android.view.Gravity
import android.widget.TableLayout
import androidx.core.content.ContextCompat
import android.content.Context
import android.util.Log
import android.widget.TextView
import android.widget.LinearLayout
import com.timfeid.njd.R
import com.timfeid.njd.ui.BoldFontTextView
import com.timfeid.njd.ui.FontTextView


class BoxscoreLayout(context: Context, protected val string: String) : LinearLayout(context) {

    internal var stat: TextView? = null
    internal var home: TextView? = null
    internal var away: TextView? = null
    val white = ContextCompat.getColor(getContext(), R.color.colorAccent)

    init {
        setWeightSum(3f)
        setPadding(0, 5, 0, 5)

        home = BoldFontTextView(context, null)
        stat = FontTextView(context, null)
        away = BoldFontTextView(context, null)

        addView(home)
        addView(stat)
        addView(away)

        setupView(home!!)
        setupView(stat!!)
        setupView(away!!)

        stat!!.setTextColor(ContextCompat.getColor(getContext(), R.color.transparentMax))
        stat!!.gravity = Gravity.CENTER
        away!!.gravity = Gravity.END

        stat!!.text = string

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
        if (home != null) {
            home!!.text = text

        }
    }

    fun setAway(text: String) {
        if (away != null) {
            away!!.text = text

        }
    }
}
