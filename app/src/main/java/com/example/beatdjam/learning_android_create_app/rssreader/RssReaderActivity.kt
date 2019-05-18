package com.example.beatdjam.learning_android_create_app.rssreader

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.LoaderManager
import android.support.v4.content.Loader
import android.support.v7.widget.GridLayoutManager
import com.example.beatdjam.learning_android_create_app.R
import kotlinx.android.synthetic.main.activity_rss_reader.articles

class RssReaderActivity : AppCompatActivity(), LoaderManager.LoaderCallbacks<Rss> {
    override fun onCreateLoader(id: Int, args: Bundle?) = RssLoader(this)
    override fun onLoaderReset(loader: Loader<Rss>) {}
    override fun onLoadFinished(loader: Loader<Rss>, data: Rss?) {
        if (data != null) {
            val recyclerView = articles
            val adapter = ArticleAdapter(this, data.articles) {
                // TODO 記事クリック時の挙動
            }
            recyclerView.adapter = adapter
            recyclerView.layoutManager = GridLayoutManager(this, 2)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rss_reader)

        supportLoaderManager.initLoader(1, null, this)
    }
}