package kr.co.knowledgerally.domain.repo

interface UserRepository {

    suspend fun isOnboarded(): Result<Boolean>
}