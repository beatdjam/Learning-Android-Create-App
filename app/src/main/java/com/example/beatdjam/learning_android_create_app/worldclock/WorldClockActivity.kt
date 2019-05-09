package com.example.beatdjam.learning_android_create_app.worldclock

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.beatdjam.learning_android_create_app.R
import kotlinx.android.synthetic.main.activity_world_clock.add
import kotlinx.android.synthetic.main.activity_world_clock.clockList
import kotlinx.android.synthetic.main.activity_world_clock.timeZone
import java.util.TimeZone

class WorldClockActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_world_clock)

        timeZone.text = TimeZone.getDefault().displayName

        showWorldClocks()

        clockList.setOnItemLongClickListener { parent, _, position, _ ->
            removeTimeZone(parent.getItemAtPosition(position) as String)
            true
        }

        add.setOnClickListener {
            val intent = Intent(this, TimeZoneSelectActivity::class.java)
            startActivityForResult(intent, TIME_ZONE_SELECT_ACTVITY)
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == TIME_ZONE_SELECT_ACTVITY
            && resultCode == Activity.RESULT_OK
            && data != null
        ) {
            val timeZone = data.getStringExtra("timeZone")

            val pref = getSharedPreferences("world_clock_prefs", Context.MODE_PRIVATE)
            val timeZones = requireNotNull(pref.getStringSet("time_zone", setOf())).plus(timeZone)
            pref.edit().putStringSet("time_zone", timeZones).apply()

            showWorldClocks()
        }
    }

    private fun removeTimeZone(value : String){
        val pref = getSharedPreferences("world_clock_prefs", Context.MODE_PRIVATE)
        val timeZones = requireNotNull(pref.getStringSet("time_zone", setOf())).minus(value)
        pref.edit().putStringSet("time_zone", timeZones).apply()
        showWorldClocks()
    }

    private fun showWorldClocks() {
        val pref = getSharedPreferences("world_clock_prefs", Context.MODE_PRIVATE)
        val timeZones = requireNotNull(pref.getStringSet("time_zone", setOf()))

        clockList.adapter = TimeZoneAdapter(this, timeZones.toList())
    }

    companion object {
        const val TIME_ZONE_SELECT_ACTVITY = 1
    }
}
