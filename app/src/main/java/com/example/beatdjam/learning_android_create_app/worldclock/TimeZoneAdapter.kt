package com.example.beatdjam.learning_android_create_app.worldclock

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.example.beatdjam.learning_android_create_app.R
import kotlinx.android.synthetic.main.activity_world_clock.view.timeZone
import kotlinx.android.synthetic.main.list_time_zone_row.view.clock
import java.util.TimeZone

class TimeZoneAdapter(
    context: Context,
    private val timeZones: List<String> = TimeZone.getAvailableIDs().toList()
) : BaseAdapter() {
    private val inflater = LayoutInflater.from(context)


    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view = convertView ?: createView(parent)

        val viewHolder = view.tag as ViewHolder
        viewHolder.also { vh ->
            val timeZone = getTimeZoneFromPosition(position)
            @SuppressLint("SetTextI18n")
            vh.name.text = "${timeZone.displayName}(${timeZone.id})"
            vh.clock.timeZone = timeZone.id
        }

        return view
    }

    override fun getItem(position: Int) = timeZones[position]

    override fun getItemId(position: Int) = position.toLong()

    override fun getCount() = timeZones.size

    private fun createView(parent: ViewGroup?) : View {
        return inflater.inflate(R.layout.list_time_zone_row, parent, false).also {
            it.tag = ViewHolder(it)
        }
    }

    private fun getTimeZoneFromPosition(position : Int) : TimeZone {
        val timeZoneId = getItem(position)
        return TimeZone.getTimeZone(timeZoneId)
    }

    private class ViewHolder(view: View) {
        val name = requireNotNull(view.timeZone)
        val clock = requireNotNull(view.clock)
    }
}