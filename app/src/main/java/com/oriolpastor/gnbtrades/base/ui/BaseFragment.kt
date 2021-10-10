package com.oriolpastor.gnbtrades.base.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel

abstract class BaseFragment<Binding: ViewDataBinding, VM : ViewModel> : Fragment() {

    abstract val layoutRes: Int
    protected abstract val viewModel: VM

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<Binding>(inflater, layoutRes, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        init(binding)
        return binding.root
    }

    open fun init(binding: Binding) {}
}