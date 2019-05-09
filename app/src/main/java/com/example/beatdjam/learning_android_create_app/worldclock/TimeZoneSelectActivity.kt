package com.example.beatdjam.learning_android_create_app.worldclock

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.beatdjam.learning_android_create_app.R
import kotlinx.android.synthetic.main.activity_time_zone_select.clockList

class TimeZoneSelectActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_time_zone_select)

        setResult(Activity.RESULT_CANCELED)
        clockList.also{ list ->
            val adapter = TimeZoneAdapter(this)
            list.adapter = adapter
            list.setOnItemClickListener { _, _, position, _ ->
                val timeZone = adapter.getItem(position)

                val result = Intent()
                result.putExtra("timeZone", timeZone)
                setResult(Activity.RESULT_OK, result)
                finish()
            }
        }
    }
}
