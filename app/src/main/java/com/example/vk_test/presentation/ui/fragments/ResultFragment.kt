package com.example.vk_test.presentation.ui.fragments

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.vk_test.R
import com.example.vk_test.databinding.FragmentResultBinding
import com.example.vk_test.presentation.core.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class ResultFragment : BaseFragment<FragmentResultBinding, MainViewModel>(R.layout.fragment_result) {

    override val viewModel by viewModel<MainViewModel>()
    override val viewBinding by viewBinding(FragmentResultBinding::bind)
    private val navArgs by navArgs<ResultFragmentArgs>()

    override fun initialize() {
        viewModel.convert(
            to = navArgs.to,
            from = navArgs.from,
            amount = navArgs.amount
        )
    }

    @SuppressLint("SetTextI18n")
    override fun launchObservers() {
        viewModel.convertState.spectateUiState(
            progressBar = viewBinding.progressBar,
            error = {
                Toast.makeText(requireContext(), it, Toast.LENGTH_LONG).show()
            },
            success = {
                viewBinding.tvResult.text = "${it.result.values.first()} ${it.result.keys.first()}"
            }
        )
    }
}