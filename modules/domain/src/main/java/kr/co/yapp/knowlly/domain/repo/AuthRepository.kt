package kr.co.yapp.knowlly.domain.repo

interface AuthRepository {

    suspend fun getAccessToken(): Result<String>
}
