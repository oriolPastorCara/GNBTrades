package com.oriolpastor.gnbtrades.feature.splashScreen.ui

import com.oriolpastor.gnbtrades.R
import com.oriolpastor.gnbtrades.base.ui.BaseFragment
import com.oriolpastor.gnbtrades.databinding.SplashFragmentBinding
import org.koin.android.viewmodel.ext.android.viewModel
import androidx.appcompat.app.AppCompatActivity

class SplashFragment: BaseFragment<SplashFragmentBinding, SplashViewModel>() {

    override val layoutRes: Int = R.layout.splash_fragment
    override val viewModel: SplashViewModel by viewModel()

    override fun onResume() {
        super.onResume()
        (activity as AppCompatActivity).supportActionBar?.hide()
    }

    override fun onStop() {
        super.onStop()
        (activity as AppCompatActivity).supportActionBar?.show()
    }
}