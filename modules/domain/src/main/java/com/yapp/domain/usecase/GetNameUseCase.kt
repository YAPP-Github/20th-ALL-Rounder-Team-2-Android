package com.yapp.domain.usecase

import com.yapp.domain.repo.UserRepository
import javax.inject.Inject

class GetNameUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    
    suspend operator fun invoke(): Result<String> {
        return userRepository.getName()
    }
}