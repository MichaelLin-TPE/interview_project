package com.interview.interviewproject.list

import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ListRepositoryImpl : ListRepository {


    private lateinit var onRequestApiListener: ListRepository.OnRequestApiListener

    override fun onRequestApi(onRequestApiListener: ListRepository.OnRequestApiListener) {
        this.onRequestApiListener = onRequestApiListener
        val httpConnectionTool = HttpConnectionTool()
        httpConnectionTool.execute("https://jsonplaceholder.typicode.com/photos")
        httpConnectionTool.setOnConnectionListener(onConnectionListener)
    }

    private var onConnectionListener = object :HttpConnectionTool.OnConnectionListener{
        override fun onSuccessful(result: String) {
            Log.i("Michael","成功取得JSON : $result")
            val gson = Gson()
            val dataArray :ArrayList<ListData> = gson.fromJson(result,object : TypeToken<List<ListData>>(){}.type)
            if (dataArray.isNotEmpty()){
                onRequestApiListener.onSuccess(dataArray)
            }
        }

        override fun onFailure(errorCode: String) {
            onRequestApiListener.onFail(errorCode)
        }

    }

}