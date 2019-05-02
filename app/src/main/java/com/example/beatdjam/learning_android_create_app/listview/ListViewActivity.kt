package com.example.beatdjam.learning_android_create_app.listview

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import com.example.beatdjam.learning_android_create_app.R
import java.util.*

class ListViewActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_view)

        val timeZones = TimeZone.getAvailableIDs()
        val adapter = ArrayAdapter<String>(
            this,
            R.layout.list_time_zone_row,
            R.id.timeZone,
            timeZones
        )

        findViewById<ListView>(R.id.timeZoneList).also {
            it.adapter = adapter
            it.setOnItemClickListener { _, _, position, _ ->
                val timeZone = adapter.getItem(position)
                Toast.makeText(this, timeZone, Toast.LENGTH_SHORT).show()
            }
        }
    }
}
