package kr.co.knowledgerally.domain.repo

interface AppMetaRepository {

    suspend fun isWelcomeShown(): Result<Boolean>

    suspend fun shownWelcome(): Result<Unit>
}
