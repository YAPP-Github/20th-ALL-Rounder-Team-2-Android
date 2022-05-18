package kr.co.knowledgerally.ui

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

@HiltViewModel
class MainViewModel @Inject constructor() : ViewModel() {

    private val _name = MutableStateFlow("")
    val name = _name.asStateFlow()
}
