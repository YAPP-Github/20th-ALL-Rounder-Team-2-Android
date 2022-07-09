package kr.co.knowledgerally.data.repo

import kr.co.knowledgerally.data.model.toDomain
import kr.co.knowledgerally.data.source.CategoryRemoteDataSource
import kr.co.knowledgerally.domain.model.Category
import kr.co.knowledgerally.domain.repo.CategoryRepository
import javax.inject.Inject

internal class CategoryRepositoryImpl @Inject constructor(
    private val dataSource: CategoryRemoteDataSource,
) : CategoryRepository {

    override suspend fun getCategoryList(): Result<List<Category>> = dataSource.getCategoryList()
        .map { it.map { it.toDomain() } }
}
