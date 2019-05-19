package com.example.beatdjam.learning_android_create_app.file

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.beatdjam.learning_android_create_app.R
import kotlinx.android.synthetic.main.list_file_row.view.fileName
import java.io.File

class FilesAdapter(
    context: Context,
    private val files : List<File>,
    private val onClick : (File) -> Unit
) : RecyclerView.Adapter<FilesAdapter.FileViewHolder>() {
    private val inflater = LayoutInflater.from(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FileViewHolder {
        val view = inflater.inflate(R.layout.list_file_row, parent, false)
        val viewHolder = FileViewHolder(view)

        view.setOnClickListener { onClick(files[viewHolder.adapterPosition]) }
        return viewHolder
    }

    override fun getItemCount() = files.size

    override fun onBindViewHolder(holder: FileViewHolder, position: Int) {
        holder.fileName.text = files[position].name
    }

    class FileViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val fileName = view.fileName
    }
}