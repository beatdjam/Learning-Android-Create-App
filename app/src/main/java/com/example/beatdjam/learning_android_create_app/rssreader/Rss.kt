package com.example.beatdjam.learning_android_create_app.rssreader

import android.content.Context
import android.support.v4.content.AsyncTaskLoader
import org.w3c.dom.Node
import org.w3c.dom.NodeList
import java.io.InputStream
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import javax.xml.parsers.DocumentBuilderFactory
import javax.xml.xpath.XPathConstants
import javax.xml.xpath.XPathFactory

data class Rss(val title : String, val pubDate : Date, val articles : List<Article>)
data class Article(val title : String, val link : String, val pubDate : Date)

fun parseRss(stream: InputStream) : Rss {
    val doc = stream.use {
        DocumentBuilderFactory.newInstance()
            .newDocumentBuilder()
            .parse(stream)
    }

    val formatter = SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss z", Locale.US)

    val xPath = XPathFactory.newInstance().newXPath()
    val items =XmlUtil.asList(
        xPath.evaluate("/rss/channel//item", doc, XPathConstants.NODESET) as NodeList
    )

    val articles = items.map{
        Article(
            title = xPath.evaluate("./title/text()", it),
            link = xPath.evaluate("./link/text()", it),
            pubDate = formatter.parse(xPath.evaluate("./pubDate/text()", it))
        )
    }

    return Rss (
        title = xPath.evaluate("/rss/channel/title/text()", doc),
        pubDate = formatter.parse(xPath.evaluate("/rss/channel/pubDate/text()", doc)),
        articles = articles
    )
}

class RssLoader(context : Context) : AsyncTaskLoader<Rss>(context) {
    private var cache : Rss? = null

    override fun loadInBackground(): Rss? {
        val response =  httpGet("https://www.sbbit.jp/rss/HotTopics.rss")
        return if (response != null) {
            parseRss(response)
        } else null
    }

    override fun deliverResult(data: Rss?) {
        if (isReset || data == null) return

        cache = data
        super.deliverResult(data)
    }

    override fun onStartLoading() {
        if (cache != null) deliverResult(cache)

        if (takeContentChanged() || cache == null) {
            forceLoad()
        }
    }

    override fun onStopLoading() {
        cancelLoad()
    }

    override fun onReset() {
        super.onReset()
        onStopLoading()
        cache = null
    }
}

object XmlUtil {
    fun asList(n: NodeList): List<Node> {
        return if (n.length == 0) emptyList()
                else NodeListWrapper(n)
    }

    class NodeListWrapper(private val list: NodeList) : AbstractList<Node>() {
        override val size: Int
            get() = list.length

        override fun get(index: Int): Node {
            return list.item(index)
        }
    }
}