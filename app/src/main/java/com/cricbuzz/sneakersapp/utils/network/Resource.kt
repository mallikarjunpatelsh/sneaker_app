package com.cricbuzz.sneakersapp.utils.network

import com.cricbuzz.sneakersapp.utils.AppConstants.NetworkConstants.Companion.DEFAULT_ERROR_CODE

/**
 * Resource
 * This is the data class to handle remote and local response
 * This class sends the success if reponse is success
 * @param T
 * @property status - success, error, loading
 * @property data - generic type of data from response
 * @property message - message from response
 * @property code - reponse code
 * @property state - used to show and hide progress bar
 * @constructor Create empty Resource
 */
data class Resource<out T>(val status: Status, val data: T?, val message: String?, val code: Int, val state: Boolean) {

    enum class Status {
        SUCCESS,
        ERROR,
        LOADING
    }

    companion object {
        fun <T> success(data: T, message: String, code: Int, state: Boolean): Resource<T> {
            return Resource(Status.SUCCESS, data, message, code, state)
        }

        fun <T> error(data: T? = null, message: String, code: Int, state: Boolean): Resource<T> {
            return Resource(Status.ERROR, data, message, code, state)
        }

        fun <T> loading(state: Boolean): Resource<T> {
            return Resource(Status.LOADING, null, null, DEFAULT_ERROR_CODE, state)
        }
    }
}