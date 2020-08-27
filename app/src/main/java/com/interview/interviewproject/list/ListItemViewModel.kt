package com.interview.interviewproject.list

import android.graphics.Bitmap

import androidx.lifecycle.MutableLiveData

class ListItemViewModel() {

    val imageViewLiveData = MutableLiveData<Bitmap>()

    val idLiveData = MutableLiveData<Int>()

    val titleLiveData = MutableLiveData<String>()

    fun setData(listData: ListData) {

        idLiveData.value = listData.id

        titleLiveData.value = listData.title

        val downloadPicture = DownloadPicture()
        downloadPicture.execute(listData.thumbnailUrl)
        downloadPicture.setOnImageDownloadListener(object : DownloadPicture.OnImageDownloadListener{
            override fun onSuccess(bitmap: Bitmap) {
                imageViewLiveData.value = bitmap
            }

            override fun onFail(errorCode: String) {
                titleLiveData.value = errorCode
            }

        })
    }


}