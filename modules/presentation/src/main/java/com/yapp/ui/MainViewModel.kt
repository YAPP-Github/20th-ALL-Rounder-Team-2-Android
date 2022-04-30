package com.yapp.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yapp.domain.usecase.GetNameUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getNameUseCase: GetNameUseCase
) : ViewModel() {

    private val _name: MutableStateFlow<NameState> = MutableStateFlow(NameState.Loading)
    val name = _name.asStateFlow()

    init {
        viewModelScope.launch {
            _name.value = NameState.Loading

            val result = getNameUseCase()
                .onSuccess { _name.value = NameState.Success(it) }
                .onFailure { _name.value = NameState.Error }
        }
    }
}
