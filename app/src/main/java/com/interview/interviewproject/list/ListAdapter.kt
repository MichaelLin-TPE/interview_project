package com.interview.interviewproject.list

import android.content.Context
import android.graphics.Bitmap
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.interview.interviewproject.databinding.ListItemBinding

class ListAdapter(private val dataArray : ArrayList<ListData>,private val context : Context) : RecyclerView.Adapter<ListAdapter.ViewHolder>() {

    private lateinit var onListItemClickListener: OnListItemClickListener

    fun setOnListItemClickListener(onListItemClickListener: OnListItemClickListener){
        this.onListItemClickListener = onListItemClickListener
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val dataBinding = ListItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(dataBinding)
    }

    override fun getItemCount(): Int {
        return if (dataArray.isNotEmpty()) dataArray.size else 0
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setData(dataArray[position],onListItemClickListener)
    }
    inner class ViewHolder(private val dataBinding: ListItemBinding) : RecyclerView.ViewHolder(dataBinding.root){

//        private lateinit var downloadPicture: DownloadPicture

        fun setData(
            listData: ListData,
            onListItemClickListener: OnListItemClickListener
        ) {
            ImageLoaderManager.getInstance().setPhotoUrl(listData.thumbnailUrl,dataBinding.listItemImage)

            dataBinding.listItemId.text = listData.id.toString()
            dataBinding.listItemTitle.text = listData.title


//            downloadPicture = DownloadPicture()
//            downloadPicture.execute(listData.thumbnailUrl)
//            downloadPicture.setOnImageDownloadListener(object :DownloadPicture.OnImageDownloadListener{
//                override fun onSuccess(bitmap: Bitmap) {
//
//                    if (dataBinding.listItemImage.tag == 0){
//                        dataBinding.listItemImage.tag = listData.id
//                        dataBinding.listItemImage.setImageBitmap(bitmap)
//                    }else if (dataBinding.listItemImage.tag != listData.id){
//                        dataBinding.listItemImage.setImageBitmap(bitmap)
//                    }
//                }
//
//                override fun onFail(errorCode: String) {
//                    dataBinding.listItemId.text = errorCode
//                }
//            })

            dataBinding.listItemClickArea.setOnClickListener {
                onListItemClickListener.onClick(listData)
            }



        }

        init {

            val metrics = context.resources.displayMetrics
            //取得螢幕寬度
            val width: Float = context.resources.displayMetrics.widthPixels.toFloat()
            //除以4
            val singleItemSize: Float = width / 4
            //轉成DP
            val singleItemDb:Float = singleItemSize / metrics.density

            val pix :Int = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,singleItemDb,context.resources.displayMetrics)
                .toInt()
            val params = ConstraintLayout.LayoutParams(pix,pix)

            dataBinding.listItemImage.layoutParams = params

        }



    }

    interface OnListItemClickListener{
        fun onClick(data :ListData)
    }
}