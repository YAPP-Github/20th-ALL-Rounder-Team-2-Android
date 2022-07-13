package kr.co.knowledgerally.ui.register.info

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kr.co.knowledgerally.base.BaseViewModel
import kr.co.knowledgerally.domain.model.Registration
import kr.co.knowledgerally.domain.usecase.GetCategoryListUseCase
import kr.co.knowledgerally.domain.usecase.RegisterLectureInfoUseCase
import kr.co.knowledgerally.model.CategoryModel
import kr.co.knowledgerally.model.toPresentation
import javax.inject.Inject

@HiltViewModel
class RegisterInfoViewModel @Inject constructor(
    getCategoryListUseCase: GetCategoryListUseCase,
    private val registerLectureInfoUseCase: RegisterLectureInfoUseCase,
) : BaseViewModel() {

    val categories: StateFlow<List<CategoryModel>> = combine(
        flowOf(Unit).onEach { delay(500L) }, // Delay time that wait for transition time
        flow { emit(getCategoryListUseCase()) },
    ) { _, result ->
        result.fold(
            onSuccess = { categories -> categories.map { it.toPresentation() } },
            onFailure = {
                handleException(it)
                emptyList()
            }
        )
    }
        .stateIn(viewModelScope, SharingStarted.Eagerly, emptyList())

    private val _loading = MutableStateFlow(false)
    val loading = _loading.asStateFlow()

    private val _result = MutableStateFlow<RegisterResult?>(null)
    val result = _result.asStateFlow()

    fun register(registration: Registration) {
        launch {
            _loading.value = true
            registerLectureInfoUseCase(registration)
                .onSuccess { _result.value = RegisterResult(it) }
                .onFailure { handleException(it) }
            _loading.value = false
        }
    }

    fun resultConsumed() {
        _result.value = null
    }
}
