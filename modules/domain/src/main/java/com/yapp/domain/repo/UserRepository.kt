package com.yapp.domain.repo

interface UserRepository {

    suspend fun getName(): Result<String>
}