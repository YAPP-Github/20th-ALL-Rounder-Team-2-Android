package kr.co.knowledgerally.domain.repo

import kr.co.knowledgerally.domain.model.Onboard

interface UserRepository {

    suspend fun isOnboarded(): Result<Boolean>

    suspend fun submitOnboard(request: Onboard): Result<Unit>
}
