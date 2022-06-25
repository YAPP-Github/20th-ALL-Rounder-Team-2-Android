package kr.co.knowledgerally.data.source

interface UserRemoteDataSource {

    suspend fun isOnboarded(): Result<Boolean>
}