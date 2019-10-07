package com.timfeid.njd

import android.webkit.JavascriptInterface

abstract class BaseWebConnector {
    @JavascriptInterface
    fun seasonId(): String {
        return BuildConfig.API_SEASON
    }

    @JavascriptInterface
    abstract fun component(): String
}