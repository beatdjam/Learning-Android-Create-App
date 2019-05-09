package com.example.beatdjam.learning_android_create_app.sharedpreferences

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.beatdjam.learning_android_create_app.R
import kotlinx.android.synthetic.main.activity_shared_preferences.editText
import kotlinx.android.synthetic.main.activity_shared_preferences.store

class SharedPreferencesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shared_preferences)

        val pref = getSharedPreferences("file_name", Context.MODE_PRIVATE)

        val editText = editText.also {
            val storedText = pref.getString("key","未登録")
            it.setText(storedText)
        }

        store.setOnClickListener {
            val inputText = editText.text.toString()
            pref.edit().putString("key", inputText).apply()
        }
    }
}
