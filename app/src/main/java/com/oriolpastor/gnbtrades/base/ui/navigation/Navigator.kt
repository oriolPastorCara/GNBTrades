package com.oriolpastor.gnbtrades.base.ui.navigation

import androidx.lifecycle.LiveData
import androidx.navigation.NavDirections
import com.hadilq.liveevent.LiveEvent

open class Navigator {
    private val _directions = LiveEvent<Route>()
    val directions: LiveData<Route> = _directions

    open fun goTo(direction: NavDirections) {
        _directions.postValue(Route.Forward(direction))
    }

    open fun back() {
        _directions.postValue(Route.Back)
    }
}
