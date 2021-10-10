package com.oriolpastor.gnbtrades.common.livecycle

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData

inline fun <T> AppCompatActivity.observe(liveData: LiveData<T>, crossinline observer: (T) -> Unit) {
    liveData.observe(this, { observer(it) })
}

fun <T> Fragment.observe(liveData: LiveData<T>, observer: (T) -> Unit) {
    viewLifecycleOwner.observeLiveCycle(liveData, observer)
}

fun <T> LifecycleOwner.observeLiveCycle(liveData: LiveData<T>, observer: (T) -> Unit) {
    liveData.observe(this, { observer(it) })
}