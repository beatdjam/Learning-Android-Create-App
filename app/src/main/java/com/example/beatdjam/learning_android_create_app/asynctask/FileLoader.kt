package com.example.beatdjam.learning_android_create_app.asynctask

import android.content.Context
import android.support.v4.content.AsyncTaskLoader
import java.io.File

class FileLoader(context: Context, private val filePath: String) : AsyncTaskLoader<List<String>>(context) {
    private var cache : List<String>? = null
    override fun loadInBackground(): List<String>? {
        return File(filePath).reader().buffered().readLines()
    }

    override fun deliverResult(data: List<String>?) {
        if (isReset) return

        this.cache = data
        super.deliverResult(data)
    }

    override fun onStartLoading() {
        if(cache != null) {
            deliverResult(cache)
        }

        if (takeContentChanged() || cache == null){
            forceLoad()
        }
    }

    override fun onStopLoading() {
        cancelLoad()
    }

    override fun onReset() {
        super.onReset()
        onStopLoading()
        cache = null
    }
}