package com.example.beatdjam.learning_android_create_app.asynctask

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.beatdjam.learning_android_create_app.R
import kotlinx.android.synthetic.main.activity_async_task_loader.fileContent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.io.File

class CoroutinesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_async_task_loader)

        // 設定ファイル書き込み・作成
        getSharedPreferences("sample", Context.MODE_PRIVATE).edit()
            .putInt("age", 30)
            .putString("name", "Tarou")
            .commit() // applyだとcommitされる前に読み込みが走ってファイルがなくて死ぬ

        GlobalScope.launch (Dispatchers.Main) {
            val filePath = "${applicationInfo.dataDir}/shared_prefs/sample.xml"
            fileContent.text = File(filePath)
                                    .reader()
                                    .buffered()
                                    .readLines()
                                    .joinToString("\n")
        }
    }
}
