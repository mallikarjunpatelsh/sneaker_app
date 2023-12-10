package com.cricbuzz.sneakersapp.domain.usecases

import com.cricbuzz.sneakersapp.data.repository.IHomeRepository
import com.cricbuzz.sneakersapp.di.qualifier.Repository
import com.cricbuzz.sneakersapp.domain.data.SneakerDetail
import com.cricbuzz.sneakersapp.ui.model.SizeModel
import com.cricbuzz.sneakersapp.ui.model.SneakerUIModel
import com.cricbuzz.sneakersapp.utils.network.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**
 * Product detail use case
 *
 * @property repository
 * @constructor Create empty Product detail use case
 */
class ProductDetailUseCase @Inject constructor(@Repository private val repository: IHomeRepository): IProductDetailUseCase {
    /**
     * Fetch sneakers
     * emits the response given by repo
     * @param id
     * @return sneakers detail
     */
    override fun fetchSneakers(id: String): Flow<Resource<SneakerUIModel?>> = flow{
        repository.fetchSneakerDetail(id).collect {
            if (it.status == Resource.Status.SUCCESS)
                emit(Resource.success(convertdataToUImodel(it.data), it.message?:"",it.code, false))
            else
                emit(Resource.error(null, it.message?:"", it.code, false))
        }
    }

    /**
     * Get sizes
     * hardcoding the sizes
     * @return
     */
    private fun getSizes(): List<SizeModel> {
        return ArrayList<SizeModel>().apply {
            add(SizeModel("7"))
            add(SizeModel("8"))
            add(SizeModel("9"))
        }
    }

    /**
     * Convert data to uimodel
     *
     * @param sneaker
     * @return
     */
    private fun convertdataToUImodel(sneaker: SneakerDetail?): SneakerUIModel? {
        if (sneaker == null) return null
        return SneakerUIModel(
            id = sneaker.id,
            name = sneaker.name,
            imageUrl = sneaker.media?.imageUrl,
            retailPrice = sneaker.retailPrice,
            title = sneaker.title,
            sizes = getSizes()
        )
    }

}