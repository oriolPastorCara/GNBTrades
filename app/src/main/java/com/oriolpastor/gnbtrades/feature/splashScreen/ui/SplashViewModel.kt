package com.oriolpastor.gnbtrades.feature.splashScreen.ui

import androidx.lifecycle.ViewModel
import com.oriolpastor.gnbtrades.base.ui.navigation.Navigator

class SplashViewModel(
    private val navigator: Navigator,
) : ViewModel() {

    private var productsSaved = false
    private var ratesSaved = false

    init {
        navigator.goTo(SplashFragmentDirections.navigateFromSplashToProducts())
    }








}