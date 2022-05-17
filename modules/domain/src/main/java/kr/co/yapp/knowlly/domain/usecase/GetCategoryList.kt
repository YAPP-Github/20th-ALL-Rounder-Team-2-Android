package kr.co.yapp.knowlly.domain.usecase

import kr.co.yapp.knowlly.domain.model.Category
import kr.co.yapp.knowlly.domain.repo.CategoryRepository
import javax.inject.Inject

class GetCategoryList @Inject constructor(
    private val categoryRepository: CategoryRepository
) {

    suspend operator fun invoke(): List<Category> {
        return categoryRepository.getCategoryList()
    }
}
