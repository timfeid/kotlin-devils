package com.timfeid.njd.ui

import android.content.Context
import androidx.appcompat.widget.AppCompatTextView
import android.graphics.Typeface
import android.util.AttributeSet
import android.util.Log

class BoldFontTextView(context: Context, attrs: AttributeSet?) : AppCompatTextView(context, attrs) {
    init {
        val typeface = Typeface.createFromAsset(context.assets, "fonts/Oswald-Bold.ttf")
        setTypeface(typeface)
    }
}