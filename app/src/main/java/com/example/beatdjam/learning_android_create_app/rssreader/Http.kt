package com.example.beatdjam.learning_android_create_app.rssreader

import java.io.InputStream
import java.net.URL
import javax.net.ssl.HttpsURLConnection

fun httpGet(url : String) : InputStream? {
    val con = URL(url).openConnection() as HttpsURLConnection
    return con.let {
        it.apply {
            requestMethod = "GET"
            connectTimeout = 3000
            readTimeout = 5000
            instanceFollowRedirects = true
        }
        it.connect()
        if (it.responseCode in 200..299) {
            it.inputStream
        } else null
    }
}