package com.interview.interviewproject

import android.util.Log
import androidx.multidex.MultiDexApplication
import com.interview.interviewproject.list.ImageLoaderManager

class InterviewApplication : MultiDexApplication() {
    companion object{
        private var instance : InterviewApplication? = null
        fun getInstance():InterviewApplication{
            if (instance == null){
                instance = InterviewApplication()
                return instance!!
            }
            return instance!!
        }
    }


    override fun onCreate() {
        super.onCreate()
        Log.i("Michael","DexApp context onCreate")
        instance = this
        ImageLoaderManager.getInstance().init(applicationContext)
    }
}