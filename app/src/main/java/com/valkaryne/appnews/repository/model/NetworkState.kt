package com.valkaryne.appnews.repository.model

/**
 * Class describing network state during the fetching data from API
 *
 * @author Valentine Litvin
 */
class NetworkState(private val status: Status, private val message: String) {

    /**
     * @return status enum element
     */
    fun getStatus() = status

    /**
     * @return network state message
     */
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