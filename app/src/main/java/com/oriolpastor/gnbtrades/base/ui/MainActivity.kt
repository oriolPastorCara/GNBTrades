package com.oriolpastor.gnbtrades.base.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavDirections
import androidx.navigation.Navigation
import com.oriolpastor.gnbtrades.R
import com.oriolpastor.gnbtrades.base.ui.navigation.Navigator
import com.oriolpastor.gnbtrades.base.ui.navigation.Route
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {

    private val navigator: Navigator by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        navigator.directions.observe(this, { direction ->
            when (direction) {
                is Route.Forward -> navigateTo(direction.direction)
                else -> navigateBack()
            }
        })
    }

    private fun navigateTo(destination: NavDirections) {
        if (isFinishing) return
        Navigation.findNavController(this, R.id.navigation_host).apply {
            navigate(destination)
        }
    }

    private fun navigateBack() {
        Navigation.findNavController(this, R.id.navigation_host).apply {
            super.onBackPressed()
        }
    }
}
