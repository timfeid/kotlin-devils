package com.timfeid.njd.ui

import android.content.Context
import androidx.appcompat.widget.AppCompatTextView
import android.graphics.Typeface
import android.util.AttributeSet
import android.util.Log
import androidx.appcompat.widget.AppCompatButton

class BoldFontButton(context: Context, attrs: AttributeSet?) : AppCompatButton(context, attrs) {
    init {
        val typeface = Typeface.createFromAsset(context.assets, "fonts/Oswald-Bold.ttf")
        setTypeface(typeface)
    }
}