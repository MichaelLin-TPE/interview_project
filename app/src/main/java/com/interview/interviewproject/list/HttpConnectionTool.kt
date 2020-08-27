package com.interview.interviewproject.list

import android.os.AsyncTask
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.URL
import javax.net.ssl.HttpsURLConnection

class HttpConnectionTool : AsyncTask<String?, Void?, String>() {

    private var listener: OnConnectionListener? = null

    fun setOnConnectionListener(listener: OnConnectionListener?) {
        this.listener = listener
    }


    override fun onPostExecute(result: String) {
        super.onPostExecute(result)
        if (!result.startsWith("Error")) {
            listener!!.onSuccessful(result)
        } else {
            listener!!.onFailure(result)
        }
    }

    interface OnConnectionListener {
        fun onSuccessful(result: String)
        fun onFailure(errorCode: String)
    }

    override fun doInBackground(vararg p0: String?): String {
        var resultStr: String
        var connection: HttpsURLConnection? = null
        var reader: BufferedReader? = null
        val response: StringBuilder
        try {
            val url = URL(p0[0])
            connection = url.openConnection() as HttpsURLConnection
            connection.requestMethod = "GET"
            connection.readTimeout = 15 * 1000
            connection.connect()
            reader = BufferedReader(InputStreamReader(connection.inputStream))
            response = StringBuilder()
            var line: String? = null
            while (reader.readLine().also { line = it } != null) {
                response.append(line)
                response.append("\r")
            }
            resultStr = response.toString()
        } catch (e: Exception) {
            e.printStackTrace()
            resultStr = "Error : $e"
        } finally {
            if (reader != null) {
                try {
                    reader.close()
                    connection!!.disconnect()
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
        return resultStr
    }
}