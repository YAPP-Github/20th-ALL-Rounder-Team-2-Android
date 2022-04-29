package com.yapp.data.source

interface UserLocalDataSource {
    
    suspend fun getName(): Result<String?>
}