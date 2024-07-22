package com.example.vk_test.presentation.ui.fragments

import com.example.vk_test.domain.model.ConvertResponse
import com.example.vk_test.domain.model.Currencies
import com.example.vk_test.domain.repository.MainRepository
import com.example.vk_test.presentation.core.BaseViewModel
import kotlinx.coroutines.flow.asStateFlow

class MainViewModel(
    private val repository: MainRepository
): BaseViewModel() {

    private val _convertState = mutableUiStateFlow<ConvertResponse>()
    val convertState = _convertState.asStateFlow()

    fun convert(to: String, from: String, amount: Int?) {
        repository.convert(to, from, amount).gatherRequest(_convertState)
    }
}