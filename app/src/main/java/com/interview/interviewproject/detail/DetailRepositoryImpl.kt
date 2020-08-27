package com.interview.interviewproject.detail

import android.graphics.Bitmap
import com.interview.interviewproject.list.DownloadPicture
import com.interview.interviewproject.list.ImageLoaderManager
import com.interview.interviewproject.list.ListData
import java.io.Serializable

class DetailRepositoryImpl(private val listData: ListData?) : DetailRepository {
    override fun getAllNeedsData(onCatchAllDataListener: DetailRepository.OnCatchAllDataListener) {
        if (listData == null){
            onCatchAllDataListener.onFail("listData is null")
            return
        }


        val image =  ImageLoaderManager.getInstance().getImage(listData.thumbnailUrl)
        val id = "id : ${listData.id}"
        val title = "title : ${listData.title}"

        if (image == null){
            onCatchAllDataListener.onFail("取得照片失敗")
            return
        }
        onCatchAllDataListener.onSuccess(id,title,image)

        val downloadPicture = DownloadPicture()
        downloadPicture.execute(listData.thumbnailUrl)
        downloadPicture.setOnImageDownloadListener(object : DownloadPicture.OnImageDownloadListener{
            override fun onSuccess(bitmap: Bitmap) {

                onCatchAllDataListener.onSuccess(id,title,bitmap)
            }

            override fun onFail(errorCode: String) {
                onCatchAllDataListener.onFail(errorCode)
            }
        })
    }
}