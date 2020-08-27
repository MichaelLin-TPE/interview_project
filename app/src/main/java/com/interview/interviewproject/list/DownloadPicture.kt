package com.interview.interviewproject.list

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.AsyncTask
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL

class DownloadPicture : AsyncTask<String, Void, Bitmap>() {

    private lateinit var onImageDownloadListener: OnImageDownloadListener

    fun setOnImageDownloadListener(onImageDownloadListener: OnImageDownloadListener){
        this.onImageDownloadListener = onImageDownloadListener
    }

    interface OnImageDownloadListener{
        fun onSuccess(bitmap: Bitmap)
        fun onFail(errorCode :String)
    }

    override fun doInBackground(vararg p0: String?): Bitmap {
        var input :InputStream? = null
        try {
            val url = URL(p0[0])
            val connection =
                url.openConnection() as HttpURLConnection
            connection.doInput = true
            connection.setRequestProperty( "User-Agent",
                "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/41.0.2228.0 Safari/537.36")
            connection.connect()
            input = connection.inputStream
            return BitmapFactory.decodeStream(input)

        }catch (e : Exception){
            e.printStackTrace()
            return null!!
        }
        return null!!
    }

    override fun onPostExecute(result: Bitmap?) {
        super.onPostExecute(result)
        if (result == null){
            onImageDownloadListener.onFail("取得圖片失敗")
            return
        }
        onImageDownloadListener.onSuccess(result)
    }
}