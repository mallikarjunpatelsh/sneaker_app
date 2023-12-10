package com.cricbuzz.sneakersapp.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.cricbuzz.sneakersapp.R
import com.cricbuzz.sneakersapp.databinding.FragmentProductsListBinding
import com.cricbuzz.sneakersapp.ui.adapter.ProductsListAdapter
import com.cricbuzz.sneakersapp.ui.callbacks.IProductListClickCallback
import com.cricbuzz.sneakersapp.ui.viewmodel.ProductListViewModel
import com.cricbuzz.sneakersapp.utils.network.Resource
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ProductsListFragment : BaseFragment(), IProductListClickCallback {
    private val viewmodel: ProductListViewModel by viewModels()
    private val listAdapter by lazy { ProductsListAdapter() }
    private lateinit var binding: FragmentProductsListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (!::binding.isInitialized) {
            binding = FragmentProductsListBinding.inflate(layoutInflater)
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewmodel.fetchSneakersList()
    }

    override fun getBundleParameter() { }

    override fun initViews() {
        binding.rvProducts.apply {
            listAdapter.setCallback(this@ProductsListFragment)
            adapter = listAdapter
            layoutManager = GridLayoutManager(context, 2)
            setHasFixedSize(true)
        }
    }

    override fun setObservers() {
        lifecycleScope.launch {
            /**
             * observing for sneakers list
             */
            viewmodel.sneakersList.collect {
                setLoadingState(it.state, it.status, it.message)
                when (it.status) {
                    Resource.Status.SUCCESS -> {
                        listAdapter.setProductsList(it.data)
                    } else -> {}
                }
            }
        }
    }

    /**
     * Product on click
     * navigates to the product details screen
     * @param id
     */
    override fun productOnClick(id: String) {
        val bundle = Bundle()
        bundle.putString("id", id)
        findNavController().navigate(R.id.productDetailsFragment, bundle)
    }
}