package com.interview.interviewproject.list

interface ListRepository {
    fun onRequestApi(onRequestApiListener: ListRepository.OnRequestApiListener)


    interface OnRequestApiListener{
        fun onSuccess(dataArray :ArrayList<ListData>)
        fun onFail(errorCode:String)
    }
}