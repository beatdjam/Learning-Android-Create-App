package com.example.beatdjam.learning_android_create_app.asynctask

import android.content.Context
import android.os.Bundle
import android.support.v4.app.LoaderManager
import android.support.v4.content.Loader
import android.support.v7.app.AppCompatActivity
import com.example.beatdjam.learning_android_create_app.R
import kotlinx.android.synthetic.main.activity_async_task_loader.fileContent

class AsyncTaskLoaderActivity : AppCompatActivity(), LoaderManager.LoaderCallbacks<List<String>> {
    override fun onCreateLoader(id: Int, args: Bundle?): Loader<List<String>> {
        val filePath = args?.getString("file_path")
            ?: throw IllegalArgumentException("file path not specified")
        return FileLoader(this, filePath)
    }

    override fun onLoadFinished(loader: Loader<List<String>>, data: List<String>?) {
        if (data != null) {
            fileContent.text = data.joinToString("\n")
        }
    }

    override fun onLoaderReset(p0: Loader<List<String>>) {}
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_async_task_loader)

        // 設定ファイル書き込み・作成
        getSharedPreferences("sample", Context.MODE_PRIVATE).edit()
            .putInt("age", 30)
            .putString("name", "Tarou")
            .commit() // applyだとcommitされる前にonCreateLoaderが走ってファイルがなくて死ぬ

        val argments = Bundle().also {
            it.putString("file_path", "${applicationInfo.dataDir}/shared_prefs/sample.xml")
        }

        // deplicatedなのでワークアラウンド探したいけど、そもそもCoroutines使えばよいのでは
        // 本のとおりだとloaderManagerだけど、そのままだとそもそも第3引数がうまく動かない
        // Target SDK の差分のせいかも
        supportLoaderManager.initLoader(1, argments, this)
    }
}
