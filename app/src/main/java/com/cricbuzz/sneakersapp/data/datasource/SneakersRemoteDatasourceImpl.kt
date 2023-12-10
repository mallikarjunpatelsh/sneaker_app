package com.cricbuzz.sneakersapp.data.datasource

import android.content.Context
import com.cricbuzz.sneakersapp.data.dao.ICheckoutDao
import com.cricbuzz.sneakersapp.domain.data.SneakerDetail
import com.cricbuzz.sneakersapp.domain.entities.ProductEntity
import com.cricbuzz.sneakersapp.utils.AppUtils
import com.cricbuzz.sneakersapp.utils.network.Resource
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Home datasource impl
 *
 * @property context
 * @property checkoutDao
 * @constructor Create empty Home datasource impl
 */
class SneakersRemoteDatasourceImpl @Inject constructor(
    @ApplicationContext private val context: Context,
    private val checkoutDao: ICheckoutDao
): ISneakersRemoteDatasource, BaseDataSource(context) {

    /**
     * Fetch sneakers
     * fetches the list of sneakers from remote or local json
     * @return flow of sneakers list
     */
    override fun fetchSneakers(): Flow<Resource<List<SneakerDetail>?>> = getResult {
        delay(500)
        AppUtils.readSneakersLocalJson(context)
    }

    /**
     * Fetch sneaker detail
     * fetches the particular sneaker detail given id by remote or local json
     * @param id - sneaker id to be fetched
     * @return flow of sneaker detail
     */
    override fun fetchSneakerDetail(id: String): Flow<Resource<SneakerDetail?>> = getResult {
        AppUtils.readSneakersLocalDetail(context, id)
    }

}