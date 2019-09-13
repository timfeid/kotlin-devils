package com.timfeid.njd.ui

import android.content.Context
import androidx.appcompat.widget.AppCompatTextView
import android.graphics.Typeface
import android.util.AttributeSet


class FontTextView(context: Context, attrs: AttributeSet?) : AppCompatTextView(context, attrs) {
    init {
        val typeface = Typeface.createFromAsset(context.assets, "fonts/Oswald-Light.ttf")
        setTypeface(typeface)
    }
}