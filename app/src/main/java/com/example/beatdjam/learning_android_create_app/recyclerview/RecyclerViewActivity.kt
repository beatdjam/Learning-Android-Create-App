package com.example.beatdjam.learning_android_create_app.recyclerview

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.beatdjam.learning_android_create_app.R
import kotlinx.android.synthetic.main.activity_recycler_view.timeZoneList
import kotlinx.android.synthetic.main.recycler_list_timezone_row.view.recyclerTimeZone
import java.text.FieldPosition
import java.util.TimeZone

class RecyclerViewActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler_view)

        val recyclerView = timeZoneList
        val adapter = SampleAdapter(this) {
            Toast.makeText(this, it.displayName, Toast.LENGTH_SHORT).show()
        }

        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager (this, LinearLayoutManager.VERTICAL, false)
    }
}

class SampleAdapter(context : Context, private val onItemClicked : (TimeZone) -> Unit)
    : RecyclerView.Adapter<SampleAdapter.SampleViewHolder>() {
    private val inflater = LayoutInflater.from(context)
    private val timeZones = TimeZone.getAvailableIDs().map { TimeZone.getTimeZone(it) }

    override fun getItemCount() = timeZones.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SampleViewHolder {
        val view = inflater.inflate(R.layout.recycler_list_timezone_row, parent, false)
        val viewHolder = SampleViewHolder(view)

        view.setOnClickListener {
            val position = viewHolder.adapterPosition
            val timeZone = timeZones[position]
            onItemClicked(timeZone)
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: SampleViewHolder, position: Int) {
        holder.timeZone.text = timeZones[position].id
    }


    class SampleViewHolder(view : View) : RecyclerView.ViewHolder(view) {
        val timeZone = view.recyclerTimeZone
    }
}
