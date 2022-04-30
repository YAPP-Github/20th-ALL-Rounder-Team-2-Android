package com.yapp.ui

sealed class NameState {
    object Loading : NameState()
    data class Success(val name: String) : NameState()
    object Error : NameState()
}