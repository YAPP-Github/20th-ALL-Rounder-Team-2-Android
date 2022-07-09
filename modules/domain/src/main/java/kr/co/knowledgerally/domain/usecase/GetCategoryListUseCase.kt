package kr.co.knowledgerally.domain.usecase

import kr.co.knowledgerally.domain.model.Category
import kr.co.knowledgerally.domain.repo.CategoryRepository
import javax.inject.Inject

class GetCategoryListUseCase @Inject constructor(
    private val repository: CategoryRepository,
) {

    suspend operator fun invoke(): Result<List<Category>> = repository.getCategoryList()
}
