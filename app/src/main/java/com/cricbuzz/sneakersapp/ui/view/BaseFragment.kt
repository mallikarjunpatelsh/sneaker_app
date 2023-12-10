package com.cricbuzz.sneakersapp.ui.view

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.cricbuzz.sneakersapp.ui.activity.MainActivity
import com.cricbuzz.sneakersapp.R
import com.cricbuzz.sneakersapp.utils.network.Resource

/**
 * Base fragment
 * Base fragment for all other fragment
 * @constructor Create empty Base fragment
 */
abstract class BaseFragment: Fragment() {
    /**
     * On view created
     * this methods call getBundleParameter(), setObservers(), initViews()
     * so avoiding redudant code in child fragment
     * @param view
     * @param savedInstanceState
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getBundleParameter()
        initViews()
        setObservers()
    }

    abstract fun getBundleParameter()

    abstract fun setObservers()

    abstract fun initViews()

    /**
     * Set loading state
     * This displays the progress bar according to  state
     * @param state
     * @param status
     * @param message
     */
    fun setLoadingState(state: Boolean, status: Resource.Status, message: String?) {
        (activity as? MainActivity)?.setLoadingState(state)
        if (status == Resource.Status.ERROR) {
            showToast(message)
        }
    }

    /**
     * Show toast
     * displays toast
     * @param message
     */
    fun showToast(message: String?) {
        Toast.makeText(context,
            message?:getString(R.string.something_went_wrong),
            Toast.LENGTH_SHORT
        ).show()
    }
}