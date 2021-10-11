package com.oriolpastor.gnbtrades.feature.splashScreen

import com.oriolpastor.gnbtrades.feature.splashScreen.ui.SplashViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val splashModule = module {
    viewModel {
        SplashViewModel(get())
    }

}
