package kr.co.knowledgerally.data.repo

import kr.co.knowledgerally.data.source.AppMetaLocalDataSource
import kr.co.knowledgerally.domain.repo.AppMetaRepository
import javax.inject.Inject

internal class AppMetaRepositoryImpl @Inject constructor(
    private val appMetaLocalDataSource: AppMetaLocalDataSource,
) : AppMetaRepository {

    override suspend fun isWelcomeShown(): Result<Boolean> =
        appMetaLocalDataSource.isWelcomeShown()
}
