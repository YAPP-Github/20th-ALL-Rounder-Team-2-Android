package kr.co.knowledgerally.ui.register.info

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import kr.co.knowledgerally.base.BaseViewModel
import kr.co.knowledgerally.domain.usecase.GetCategoryListUseCase
import kr.co.knowledgerally.model.CategoryModel
import kr.co.knowledgerally.model.toPresentation
import javax.inject.Inject

@HiltViewModel
class RegisterInfoViewModel @Inject constructor(
    getCategoryListUseCase: GetCategoryListUseCase,
) : BaseViewModel() {

    val categories: StateFlow<List<CategoryModel>> = flow {
        getCategoryListUseCase()
            .map { categories -> categories.map { it.toPresentation() } }
            .onSuccess { emit(it) }
            .onFailure { handleException(it) }
    }.stateIn(viewModelScope, SharingStarted.Eagerly, emptyList())
}
