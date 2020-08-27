package com.interview.interviewproject.detail

import android.graphics.Bitmap

interface DetailRepository {
    fun getAllNeedsData(onCatchAllDataListener: DetailRepository.OnCatchAllDataListener)


    interface OnCatchAllDataListener{
        fun onSuccess(id:String,title:String,image:Bitmap)
        fun onFail(errorCode:String)
    }

}