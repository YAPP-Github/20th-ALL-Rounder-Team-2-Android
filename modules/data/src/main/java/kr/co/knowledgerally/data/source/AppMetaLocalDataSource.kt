package kr.co.knowledgerally.data.source

interface AppMetaLocalDataSource {

    suspend fun isWelcomeShown(): Result<Boolean>
}
