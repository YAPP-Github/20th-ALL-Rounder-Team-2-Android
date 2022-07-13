package kr.co.knowledgerally.data.repo

import kr.co.knowledgerally.data.model.toDomain
import kr.co.knowledgerally.data.source.CategoryRemoteDataSource
import kr.co.knowledgerally.domain.model.Category
import kr.co.knowledgerally.domain.repo.CategoryRepository
import javax.inject.Inject

internal class CategoryRepositoryImpl @Inject constructor(
    private val dataSource: CategoryRemoteDataSource,
) : CategoryRepository {

    private var categories: List<Category>? = null

    override suspend fun getCategoryList(): Result<List<Category>> =
        if (categories.isNullOrEmpty()) {
            dataSource.getCategoryList()
                .map { it.map { it.toDomain() } }
                .onSuccess { categories = it }
        } else {
            Result.success(categories!!)
        }
}
