package kr.co.yapp.knowlly.data.source

interface AuthLocalDataSource {

    suspend fun getAccessToken(): Result<String>
}
