package kr.co.yapp.knowlly.data.repo

import javax.inject.Inject

internal class UserRepositoryImpl @Inject constructor() : UserRepository {

    override fun getName(): String {
        return "Android"
    }
}