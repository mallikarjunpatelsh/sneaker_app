package com.cricbuzz.sneakersapp.ui.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cricbuzz.sneakersapp.domain.data.SneakerDetail
import com.cricbuzz.sneakersapp.domain.usecases.IProductListUseCase
import com.cricbuzz.sneakersapp.utils.network.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Product list view model
 * This viewmodel is associated with ProductListFragment
 * @property productsListUseCase
 * @constructor Create empty Product list view model
 */
@HiltViewModel
class ProductListViewModel @Inject constructor(private val productsListUseCase: IProductListUseCase): ViewModel() {
    private val _sneakersList = MutableStateFlow<Resource<List<SneakerDetail>?>>(Resource.loading(true))
    val sneakersList = _sneakersList.asStateFlow()

    /**
     * Fetch sneakers list
     * gets all the sneakers to display in home
     */
    fun fetchSneakersList() {
        viewModelScope.launch {
            productsListUseCase.fetchSneakers().collect {
                _sneakersList.emit(it)
            }
        }
    }
}