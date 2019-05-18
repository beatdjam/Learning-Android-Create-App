package com.example.beatdjam.learning_android_create_app.rssreader

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.beatdjam.learning_android_create_app.R

class RssReaderActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rss_reader)
    }
}

data class Person(
    val name : String,
    val age : Int
) {
    class Builder(
        private var name : String? = null,
        private var age : Int? = null
    ) {
        fun name(name : String) = this.also { it.name = name }
        fun age(age : Int) = this.also { it.age = age }
        fun build() = Person(this.name!!, this.age!!)
    }
    companion object {
        @JvmStatic
        fun builder() = Builder()
    }
}