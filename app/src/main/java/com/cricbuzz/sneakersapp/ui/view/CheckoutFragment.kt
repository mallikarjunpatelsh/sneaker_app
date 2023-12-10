package com.cricbuzz.sneakersapp.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.cricbuzz.sneakersapp.R
import com.cricbuzz.sneakersapp.databinding.FragmentCheckoutBinding
import com.cricbuzz.sneakersapp.ui.adapter.CartItemAdapter
import com.cricbuzz.sneakersapp.ui.callbacks.ICheckoutClickCallback
import com.cricbuzz.sneakersapp.ui.viewmodel.CheckoutViewModel
import com.cricbuzz.sneakersapp.utils.network.Resource
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CheckoutFragment : BaseFragment(), ICheckoutClickCallback {
    private lateinit var _binding: FragmentCheckoutBinding
    private val checkoutViewModel: CheckoutViewModel by viewModels()
    private val cartItemAdapter by lazy { CartItemAdapter(this) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCheckoutBinding.inflate(inflater, container, false)
        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        checkoutViewModel.getAllCartItems()
    }

    override fun getBundleParameter() { }

    override fun initViews() {
        _binding.rvCartItems.apply {
            adapter = cartItemAdapter
            layoutManager = LinearLayoutManager(context)
        }
    }

    override fun setObservers() {
        /**
         * observing for get cart items
         * once recieved UI will be updated
         */
        lifecycleScope.launch {
            checkoutViewModel.cartItems.collect {
                setLoadingState(it.state, it.status, it.message)
                when(it.status) {
                    Resource.Status.SUCCESS -> {
                        cartItemAdapter.setList(it.data)
                    }
                    else -> {}
                }
            }
        }
        /**
         * observing for delete cart item
         * once deleted calling get cart items to reload the ui
         */
        lifecycleScope.launch {
            checkoutViewModel.deleteItem.collect {
                setLoadingState(it.state, it.status, it.message)
                when(it.status) {
                    Resource.Status.SUCCESS -> {
                        showToast(getString(R.string.removed_the_item_from_cart))
                        checkoutViewModel.getAllCartItems()
                    }
                    else -> {}
                }
            }
        }
    }

    override fun onClick(id: String) {
        checkoutViewModel.deleteCartItem(id)
    }

}