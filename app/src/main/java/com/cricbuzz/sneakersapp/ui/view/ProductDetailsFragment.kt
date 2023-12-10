package com.cricbuzz.sneakersapp.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.cricbuzz.sneakersapp.R
import com.cricbuzz.sneakersapp.databinding.FragmentProductDetailsBinding
import com.cricbuzz.sneakersapp.ui.adapter.ProductImageAdapter
import com.cricbuzz.sneakersapp.ui.adapter.SizeAdapter
import com.cricbuzz.sneakersapp.ui.model.SneakerUIModel
import com.cricbuzz.sneakersapp.ui.viewmodel.ProductDetailViewmodel
import com.cricbuzz.sneakersapp.utils.network.Resource
import com.google.android.material.chip.Chip
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ProductDetailsFragment : BaseFragment() {
    private var productId:String? = null
    private var product: SneakerUIModel? = null
    private lateinit var binding: FragmentProductDetailsBinding
    private val rvImageAdapter by lazy { ProductImageAdapter() }
    private val sizeAdapter by lazy { SizeAdapter() }
    private val viewModel: ProductDetailViewmodel by viewModels()
    private val onClick = OnClickListener{
        when(it) {
            binding.buttonAddToCart -> {
                product?.let { it1 -> viewModel.addToCart(it1) }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentProductDetailsBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        productId?.let { viewModel.fetchProductDetails(it) }
    }

    override fun getBundleParameter() {
        arguments?.let {
            productId = it.getString("id")
        }
    }

    override fun initViews() {
        binding.rvImageBanner.apply {
            adapter = rvImageAdapter
        }
        binding.rvSize.apply {
            adapter = sizeAdapter
        }
        binding.buttonAddToCart.setOnClickListener(onClick)
    }

    override fun setObservers() {
        /**
         * observing for sneaker detail
         * once recieved UI will be updated
         */
        lifecycleScope.launch {
            viewModel.sneaker.collect {
                setLoadingState(it.state, it.status, it.message)
                when(it.status) {
                    Resource.Status.SUCCESS -> {
                        product = it.data
                        updateView()
                    }
                    else -> {}
                }
            }
        }
        lifecycleScope.launch {
            /**
             * observing for item added to cart
             */
            viewModel.addItemToCart.collect {
                when(it.status) {
                    Resource.Status.SUCCESS -> {
                        showToast(getString(R.string.added_item_to_cart))
                    }
                    else -> {}
                }
            }
        }
    }

    private fun updateView() {
        product?.let {
            binding.apply {
                tvProductName.text = it.name
                tvProductSubDesc.text = it.title
                tvPrice.text = getString(R.string.price, it.retailPrice.toString())
            }
            rvImageAdapter.setImages(it.imageUrl)
            sizeAdapter.setList(it.sizes)
        }
        /**
         * hardcoding the color part
         */
        binding.colorChipGroup.removeAllViews()
        binding.colorChipGroup.apply {
            val chip1 = Chip(context)
            chip1.setChipBackgroundColorResource(R.color.teal_700)
            addView(chip1)
            val chip2 = Chip(context)
            chip2.setChipBackgroundColorResource(R.color.teal_200)
            addView(chip2)
            val chip3 = Chip(context)
            chip3.setChipBackgroundColorResource(R.color.purple_200)
            addView(chip3)
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String) =
            ProductDetailsFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }

}