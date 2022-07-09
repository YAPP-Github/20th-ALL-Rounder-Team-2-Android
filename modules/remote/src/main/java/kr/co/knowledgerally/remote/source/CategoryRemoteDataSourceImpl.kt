package kr.co.knowledgerally.remote.source

import kr.co.knowledgerally.data.model.CategoryEntity
import kr.co.knowledgerally.data.source.CategoryRemoteDataSource
import kr.co.knowledgerally.remote.api.ApiService
import kr.co.knowledgerally.remote.model.toData
import javax.inject.Inject

internal class CategoryRemoteDataSourceImpl @Inject constructor(
    private val apiService: ApiService,
) : CategoryRemoteDataSource {

    override suspend fun getCategoryList(): Result<List<CategoryEntity>> = runCatching {
        val response = apiService.getCategoryList()
        response.data.map { it.toData() }
    }
}
