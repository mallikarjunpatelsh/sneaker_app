package com.cricbuzz.sneakersapp.utils

import android.content.Context
import com.cricbuzz.sneakersapp.R
import com.cricbuzz.sneakersapp.domain.data.SneakerDetail
import com.cricbuzz.sneakersapp.utils.network.Resource
import com.google.gson.Gson
import java.lang.Exception

object AppUtils {

    /**
     * Read sneakers local json
     *
     * @param context
     * @return
     */
    fun readSneakersLocalJson(context: Context): List<SneakerDetail>? {
        return try {
            val jsonString = context.resources.openRawResource(R.raw.sneakers_list).bufferedReader().use { it.readText() }
            Gson().fromJson(jsonString, Array<SneakerDetail>::class.java).asList()
        } catch (e: Exception) {
            null
        }
    }

    /**
     * Read sneaker detail of the given id from local json
     * @param context
     * @param id
     * @return
     */
    fun readSneakersLocalDetail(context: Context, id: String): SneakerDetail? {
        try {
            val list = readSneakersLocalJson(context)
            list?.forEach { if(id == it.id) return it}
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return null
    }
}