package com.timfeid.njd

import android.util.Log
import android.util.Pair

import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.io.UnsupportedEncodingException
import java.net.HttpURLConnection
import java.net.URL
import java.net.URLEncoder
import java.util.ArrayList

internal class UrlMaker(var endpoint: String) {
    var baseUrl: String = BuildConfig.API_URL
    var params: MutableList<Pair<String, String>> = ArrayList()

    fun addParam(key: String, value: String) {
        params.add(Pair.create(key, value))
    }

    protected fun buildQuery(): String {
        val result = StringBuilder()
        var first = true

        for (pair in params) {
            if (first) {
                first = false
            } else {
                result.append("&")
            }

            try {
                result.append(URLEncoder.encode(pair.first as String, "UTF-8"))
                result.append("=")
                result.append(URLEncoder.encode(pair.second as String, "UTF-8"))
            } catch (e: UnsupportedEncodingException) {
                // handle this bitch
            }

        }
        return result.toString()
    }

    fun get(): String {
        return baseUrl + endpoint + "?" + buildQuery()
    }
}