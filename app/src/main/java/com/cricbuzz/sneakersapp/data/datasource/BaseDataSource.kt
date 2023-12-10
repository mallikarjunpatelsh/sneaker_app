package com.cricbuzz.sneakersapp.data.datasource

import android.content.Context
import com.cricbuzz.sneakersapp.R
import com.cricbuzz.sneakersapp.utils.AppConstants.NetworkConstants.Companion.DEFAULT_ERROR_CODE
import com.cricbuzz.sneakersapp.utils.AppConstants.NetworkConstants.Companion.INTERNET_ERROR_CODE
import com.cricbuzz.sneakersapp.utils.network.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.net.SocketTimeoutException
import java.net.UnknownHostException

/**
 * This class is for entire project for all the modules
 */
abstract class BaseDataSource(private val context: Context) {

    /**
     * Get result
     * This function is common for both api and db operations
     * All exceptions related are handled here send to next layers
     * @param T is generic type and can be any type of block
     * @return Flow of resource based on the response
     */
    protected fun <T> getResult(call: suspend () -> T): Flow<Resource<T>> = flow {
        try {
            val response = call()
            if (response != null) {
                emit(Resource.success(response, "Success", 200, false))
            } else {
                emit(error(message = context.getString(R.string.something_went_wrong), code = DEFAULT_ERROR_CODE))
            }
        } catch (e: Exception) {
            e.printStackTrace()
            /**
             * checking for internet connection exception
             */
            if (e is SocketTimeoutException || e is UnknownHostException) {
                emit(error(message = context.getString(R.string.internet_connection), code = INTERNET_ERROR_CODE))
            } else {
                emit(error(message = context.getString(R.string.something_went_wrong), code = DEFAULT_ERROR_CODE))
            }
        }
    }

    /**
     * Error
     *
     * @param T
     * @param body
     * @param message
     * @param code
     * @return error resource
     */
    private fun <T> error(body: T? = null, message: String, code: Int): Resource<T> {
        return Resource.error(body, message, code, false)
    }
}