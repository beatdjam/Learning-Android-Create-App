package com.example.beatdjam.learning_android_create_app.rssreader

import android.app.job.JobInfo
import android.app.job.JobScheduler
import android.content.ComponentName
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.customtabs.CustomTabsIntent
import android.support.v4.app.LoaderManager
import android.support.v4.content.Loader
import android.support.v7.widget.GridLayoutManager
import com.example.beatdjam.learning_android_create_app.R
import kotlinx.android.synthetic.main.activity_rss_reader.articles
import java.util.concurrent.TimeUnit

class RssReaderActivity : AppCompatActivity(), LoaderManager.LoaderCallbacks<Rss> {
    override fun onCreateLoader(id: Int, args: Bundle?) = RssLoader(this)
    override fun onLoaderReset(loader: Loader<Rss>) {}
    override fun onLoadFinished(loader: Loader<Rss>, data: Rss?) {
        if (data != null) {
            val recyclerView = articles
            val adapter = ArticleAdapter(this, data.articles) {
                val intent = CustomTabsIntent.Builder().build()
                intent.launchUrl(this, Uri.parse(it.link))
            }
            recyclerView.adapter = adapter
            recyclerView.layoutManager = GridLayoutManager(this, 2)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rss_reader)

        supportLoaderManager.initLoader(1, null, this)

        createChannel(this)
        val fetchJob = JobInfo
            .Builder(1, ComponentName(this, PollingJob::class.java))
            .setPeriodic(TimeUnit.HOURS.toMillis(6))
            .setPersisted(true)
            .setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY)
            .build()
        getSystemService(JobScheduler::class.java).schedule(fetchJob)
    }
}