package kr.co.yapp.knowlly.local.source

import kr.co.yapp.knowlly.data.source.AuthLocalDataSource
import javax.inject.Inject

internal class AuthLocalDataSourceImpl @Inject constructor(

) : AuthLocalDataSource {

    override suspend fun getAccessToken(): Result<String> {
        TODO("SharedPreferences")
    }
}
