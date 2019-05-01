package com.example.beatdjam.learning_android_create_app.sharedpreferences

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.example.beatdjam.learning_android_create_app.R

class SharedPreferencesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shared_preferences)

        val pref = getSharedPreferences("file_name", Context.MODE_PRIVATE)

        val editText = findViewById<EditText>(R.id.editText).also {
            val storedText = pref.getString("key","未登録")
            it.setText(storedText)
        }

        findViewById<Button>(R.id.store).setOnClickListener {
            val inputText = editText.text.toString()
            pref.edit().putString("key", inputText).apply()
        }
    }
}
