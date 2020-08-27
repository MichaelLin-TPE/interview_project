package com.interview.interviewproject.list

import android.content.Context
import android.graphics.Bitmap
import android.widget.ImageView
import com.interview.interviewproject.DependencyInjection
import com.interview.interviewproject.InterviewApplication
import com.interview.interviewproject.R
import com.nostra13.universalimageloader.core.DisplayImageOptions
import com.nostra13.universalimageloader.core.ImageLoader
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration
import com.nostra13.universalimageloader.core.assist.ImageScaleType

class ImageLoaderManager  {


    private var options : DisplayImageOptions? = null

    private lateinit var imageLoader : ImageLoader

    companion object{
        private var instance : ImageLoaderManager? = null

        fun getInstance():ImageLoaderManager{
            if (instance == null){
                instance = ImageLoaderManager()
                return instance!!
            }
            return instance!!
        }

    }
    fun init(context: Context) {
        val headers = HashMap<String,String>()
        headers.put("User-Agent",
            "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/41.0.2228.0 Safari/537.36")
        imageLoader = ImageLoader.getInstance()
        options = DisplayImageOptions.Builder()
            .showImageForEmptyUri(R.mipmap.ic_launcher)
            .showImageOnFail(R.mipmap.ic_launcher)
            .showImageOnLoading(R.mipmap.ic_launcher)
            .cacheInMemory(true)
            .cacheOnDisk(true)
            .considerExifParams(true)
            .extraForDownloader(headers)
            .bitmapConfig(Bitmap.Config.RGB_565)
            .imageScaleType(ImageScaleType.EXACTLY)
            .build()
        val config = ImageLoaderConfiguration.Builder(context)
            .defaultDisplayImageOptions(options).imageDownloader(CustomDownloader(context)).build()
        imageLoader.init(config)
    }

    fun setPhotoUrl(url: String?, ivImage: ImageView?) {
        imageLoader.displayImage(url, ivImage, options)
    }
    fun getImage(url:String) : Bitmap{
        return imageLoader.loadImageSync(url,options)
    }

}