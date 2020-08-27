package com.interview.interviewproject

import android.content.Context

class DependencyInjection {

    private var context: Context? = null

    companion object{
        private val instance : DependencyInjection? = null

        fun getInstance():DependencyInjection{
            if (instance == null){
                return DependencyInjection()
            }
            return instance
        }
    }
    fun setContext(context: Context){
        this.context = context
    }
    fun getContext():Context{
        return context ?: null!!
    }

}