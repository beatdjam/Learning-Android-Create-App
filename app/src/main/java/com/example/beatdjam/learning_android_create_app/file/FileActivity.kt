package com.example.beatdjam.learning_android_create_app.file

import android.content.pm.PackageManager
import android.os.Bundle
import android.os.Environment
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.Toast
import com.example.beatdjam.learning_android_create_app.R
import kotlinx.android.synthetic.main.activity_file.fileList
import java.io.File

class FileActivity : AppCompatActivity() {

    private var currentDir: File = Environment.getExternalStorageDirectory()
    private lateinit var recyclerView: RecyclerView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_file)

        recyclerView = fileList
        recyclerView.layoutManager = LinearLayoutManager(
            this,
            LinearLayoutManager.VERTICAL,
            false
        )

        if (hasPermission()) showFiles()
    }

    override fun onBackPressed() {
        if (currentDir != Environment.getExternalStorageDirectory()) {
            currentDir = currentDir.parentFile
            showFiles()
        } else {
            super.onBackPressed()
        }
    }

    private fun showFiles() {
        val adapter = FilesAdapter(this, currentDir.listFiles().toList()) {
            if (it.isDirectory) {
                currentDir = it
                showFiles()
            } else {
                Toast.makeText(this, it.absolutePath, Toast.LENGTH_SHORT).show()
            }
        }
        recyclerView.adapter = adapter
        title = currentDir.path
    }

    private fun hasPermission(): Boolean {
        val currentPermission = ContextCompat.checkSelfPermission(
            this,
            android.Manifest.permission.READ_EXTERNAL_STORAGE
        )

        return if (currentPermission != PackageManager.PERMISSION_GRANTED) {
            val permissions = arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE)
            ActivityCompat.requestPermissions(this, permissions, 1)
            false
        } else true
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        if (grantResults.isNotEmpty()
            && grantResults[0] == PackageManager.PERMISSION_GRANTED
        ) {
            showFiles()
        } else {
            finish()
        }
    }
}
