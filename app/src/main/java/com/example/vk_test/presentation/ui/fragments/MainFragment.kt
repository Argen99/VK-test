package com.example.vk_test.presentation.ui.fragments

import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.vk_test.R
import com.example.vk_test.databinding.FragmentMainBinding
import com.example.vk_test.domain.model.Currencies
import com.example.vk_test.presentation.core.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainFragment : BaseFragment<FragmentMainBinding, MainViewModel>(R.layout.fragment_main) {

    override val viewModel by viewModel<MainViewModel>()
    override val viewBinding by viewBinding(FragmentMainBinding::bind)

    override fun initialize() {
        val spinnerAdapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_dropdown_item,
            Currencies.entries
        )
        viewBinding.spinnerCovertFrom.adapter = spinnerAdapter
        viewBinding.spinnerCovertFrom.setSelection(spinnerAdapter.getPosition(Currencies.RUB))
        viewBinding.spinnerCovertTo.adapter = spinnerAdapter
        viewBinding.spinnerCovertTo.setSelection(spinnerAdapter.getPosition(Currencies.USD))
    }

    override fun setupListeners() {
        viewBinding.btnConvert.setOnClickListener {
            findNavController().navigate(
                MainFragmentDirections.actionFirstFragmentToSecondFragment(
                    from = viewBinding.spinnerCovertFrom.selectedItem.toString(),
                    to = viewBinding.spinnerCovertTo.selectedItem.toString(),
                    amount = viewBinding.etAmount.text.toString().toIntOrNull() ?: 1
                )
            )
        }
    }
}