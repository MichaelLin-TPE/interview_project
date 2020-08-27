package com.interview.interviewproject.list

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ListViewModel(private val listRepository: ListRepository) : ViewModel() {


    val showProgress = MutableLiveData<Boolean>(true)

    val recyclerViewData = MutableLiveData<ArrayList<ListData>>()

    val errorCodeLiveData = MutableLiveData<String>()

    val isClosePage = MutableLiveData<Boolean>(false)


    fun setBackButtonClickListener(){
        isClosePage.value = true
    }

    override fun onCleared() {
        super.onCleared()
        Log.i("Michael","onCleared ListViewModel")
    }

    fun onActivityCreate() {
        listRepository.onRequestApi(onRequestApiListener)
    }

    private var onRequestApiListener = object :ListRepository.OnRequestApiListener{
        override fun onSuccess(dataArray :ArrayList<ListData>) {
            showProgress.value = false
            recyclerViewData.value = dataArray
        }

        override fun onFail(errorCode: String) {
            errorCodeLiveData.postValue(errorCode)
        }
    }

    open class Factory(private val listRepository: ListRepository) : ViewModelProvider.Factory{
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return ListViewModel(listRepository) as T
        }

    }
}