package com.interview.interviewproject.detail

import android.graphics.Bitmap
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class DetailViewModel(private val detailRepository: DetailRepository) : ViewModel() {

    val idLiveData = MutableLiveData<String>()

    val titleLiveData = MutableLiveData<String>()

    val imageLIveData = MutableLiveData<Bitmap>()

    val errorCodeLiveData = MutableLiveData<String>()
    val isClosePage = MutableLiveData<Boolean>(false)
    val isShowProgress = MutableLiveData<Boolean>(true)




    fun setBackButtonClickListener(){
        isClosePage.value = true
    }

    fun onActivityCreate() {
        detailRepository.getAllNeedsData(onCatchAllDataListener)
    }


    private var onCatchAllDataListener = object :DetailRepository.OnCatchAllDataListener{
        override fun onSuccess(id: String, title: String, image: Bitmap) {
            isShowProgress.value = false
            idLiveData.value = id
            titleLiveData.value = title
            imageLIveData.value = image
        }

        override fun onFail(errorCode: String) {
            errorCodeLiveData.value = errorCode
        }

    }


    open class Factory(private val detailRepository: DetailRepository) : ViewModelProvider.Factory{
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return DetailViewModel(detailRepository) as T
        }

    }
}