package com.oriolpastor.gnbtrades.feature.splashScreen.ui

import com.oriolpastor.gnbtrades.R
import com.oriolpastor.gnbtrades.base.ui.BaseFragment
import com.oriolpastor.gnbtrades.databinding.SplashFragmentBinding
import org.koin.android.viewmodel.ext.android.viewModel

class SplashFragment: BaseFragment<SplashFragmentBinding, Int, SplashViewModel>() {

    override val layoutRes: Int = R.layout.splash_fragment
    override val viewModel: SplashViewModel by viewModel()

}