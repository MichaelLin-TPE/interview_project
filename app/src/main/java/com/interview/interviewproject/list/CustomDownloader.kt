package com.interview.interviewproject.list

import android.content.Context
import com.nostra13.universalimageloader.core.download.BaseImageDownloader
import java.net.HttpURLConnection

class CustomDownloader(context: Context?) : BaseImageDownloader(context) {


    override fun createConnection(url: String?, extra: Any?): HttpURLConnection {

        val conn = super.createConnection(url, extra)

        val headers = extra as Map<String,String>

        if (headers != null){
            for ((key, value) in headers) {
                conn.setRequestProperty(key, value)
            }
        }


        return conn
    }
}