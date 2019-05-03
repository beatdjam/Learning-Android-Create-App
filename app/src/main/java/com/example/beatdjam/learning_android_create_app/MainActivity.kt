package com.example.beatdjam.learning_android_create_app

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.beatdjam.learning_android_create_app.calculator.CalculatorActivity
import com.example.beatdjam.learning_android_create_app.listview.ListViewActivity
import com.example.beatdjam.learning_android_create_app.sharedpreferences.SharedPreferencesActivity
import com.example.beatdjam.learning_android_create_app.worldclock.WorldClockActivity
import kotlinx.android.synthetic.main.activity_main.*

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
    }
}
