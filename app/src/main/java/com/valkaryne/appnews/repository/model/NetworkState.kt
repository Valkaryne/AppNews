package com.valkaryne.appnews.repository.model

class NetworkState (private val status: Status, private val message: String){

    fun getStatus() = status

    fun getMessage() = message

    enum class Status {
        RUNNING,
        SUCCEED,
        FAILED
    }

    companion object {
        val SUCCESS = NetworkState(Status.SUCCEED, "Succeed")
        val LOADING = NetworkState(Status.RUNNING, "Running")
    }
}