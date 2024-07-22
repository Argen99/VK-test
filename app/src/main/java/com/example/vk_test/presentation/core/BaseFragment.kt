package com.example.vk_test.presentation.core

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.annotation.LayoutRes
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.viewbinding.ViewBinding
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

abstract class BaseFragment<VB : ViewBinding, VM : BaseViewModel>(@LayoutRes layoutId: Int) :
    Fragment(layoutId) {

    protected abstract val viewModel: VM
    protected abstract val viewBinding: VB

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initialize()
        setupListeners()
        launchObservers()
    }

    protected open fun initialize() {}
    protected open fun setupListeners() {}
    protected open fun launchObservers() {}



    protected fun <T> StateFlow<UIState<T>>.spectateUiState(
        progressBar: ProgressBar? = null,
        lifecycleState: Lifecycle.State = Lifecycle.State.STARTED,
        success: ((data: T) -> Unit)? = null,
        loading: ((data: UIState.Loading<T>) -> Unit)? = null,
        error: ((error: String) -> Unit)? = null,
        idle: ((idle: UIState.Idle<T>) -> Unit)? = null,
    ) {
        safeFlowGather(lifecycleState) {
            collect {
                when (it) {
                    is UIState.Idle -> {
                        progressBar?.isVisible = false
                        idle?.invoke(it)
                    }
                    is UIState.Loading -> {
                        progressBar?.isVisible = true
                        loading?.invoke(it)
                    }

                    is UIState.Error -> {
                        progressBar?.isVisible = false
                        error?.invoke(it.error)
                    }

                    is UIState.Success -> {
                        progressBar?.isVisible = false
                        success?.invoke(it.data)
                    }
                }
            }
        }
    }

    private fun safeFlowGather(
        lifecycleState: Lifecycle.State,
        gather: suspend () -> Unit,
    ) {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(lifecycleState) {
                gather()
            }
        }
    }
}