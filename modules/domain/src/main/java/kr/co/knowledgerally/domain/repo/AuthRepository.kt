package kr.co.knowledgerally.domain.repo

interface AuthRepository {

    suspend fun getAccessToken(): Result<String>
}
