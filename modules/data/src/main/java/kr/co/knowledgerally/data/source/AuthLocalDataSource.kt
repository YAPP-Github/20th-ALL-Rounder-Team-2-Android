package kr.co.knowledgerally.data.source

interface AuthLocalDataSource {

    suspend fun getAccessToken(): Result<String>
}
