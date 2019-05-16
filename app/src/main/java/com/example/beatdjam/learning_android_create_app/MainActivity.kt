package com.example.beatdjam.learning_android_create_app

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.beatdjam.learning_android_create_app.asynctask.AsyncTaskLoaderActivity
import com.example.beatdjam.learning_android_create_app.asynctask.CoroutinesActivity
import com.example.beatdjam.learning_android_create_app.calculator.CalculatorActivity
import com.example.beatdjam.learning_android_create_app.listview.ListViewActivity
import com.example.beatdjam.learning_android_create_app.recyclerview.RecyclerViewActivity
import com.example.beatdjam.learning_android_create_app.rssreader.RssReaderActivity
import com.example.beatdjam.learning_android_create_app.sharedpreferences.SharedPreferencesActivity
import com.example.beatdjam.learning_android_create_app.worldclock.WorldClockActivity
import kotlinx.android.synthetic.main.activity_main.asynctask
import kotlinx.android.synthetic.main.activity_main.calculator
import kotlinx.android.synthetic.main.activity_main.coroutines
import kotlinx.android.synthetic.main.activity_main.listview
import kotlinx.android.synthetic.main.activity_main.recyclerview
import kotlinx.android.synthetic.main.activity_main.rssreader
import kotlinx.android.synthetic.main.activity_main.sharedpreferences
import kotlinx.android.synthetic.main.activity_main.worldclock

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        calculator.setOnClickListener {
            startActivity(Intent(this, CalculatorActivity::class.java))
        }

        sharedpreferences.setOnClickListener {
            startActivity(Intent(this, SharedPreferencesActivity::class.java))
        }

        listview.setOnClickListener {
            startActivity(Intent(this, ListViewActivity::class.java))
        }

        worldclock.setOnClickListener {
            startActivity(Intent(this, WorldClockActivity::class.java))
        }

        asynctask.setOnClickListener {
            startActivity(Intent(this, AsyncTaskLoaderActivity::class.java))
        }

        coroutines.setOnClickListener {
            startActivity(Intent(this, CoroutinesActivity::class.java))
        }

        recyclerview.setOnClickListener {
            startActivity(Intent(this, RecyclerViewActivity::class.java))
        }

        rssreader.setOnClickListener {
            startActivity(Intent(this, RssReaderActivity::class.java))
        }
    }
}
