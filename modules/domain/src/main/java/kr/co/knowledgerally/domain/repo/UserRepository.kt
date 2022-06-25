package kr.co.knowledgerally.domain.repo

import kr.co.knowledgerally.domain.model.Onboard
import kr.co.knowledgerally.domain.model.User

interface UserRepository {

    suspend fun isOnboarded(): Result<Boolean>

    suspend fun submitOnboard(request: Onboard): Result<Unit>

    suspend fun getUser(): Result<User>
}
