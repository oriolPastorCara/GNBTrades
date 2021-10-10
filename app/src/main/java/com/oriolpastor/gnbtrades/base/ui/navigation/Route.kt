package com.oriolpastor.gnbtrades.base.ui.navigation

import androidx.navigation.NavDirections

sealed class Route {
    open class Forward(val direction: NavDirections) : Route()
    object Back : Route()
}