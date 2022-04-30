package com.yapp.data.source

interface UserRemoteDataSource {

    suspend fun getName(): Result<String>
}