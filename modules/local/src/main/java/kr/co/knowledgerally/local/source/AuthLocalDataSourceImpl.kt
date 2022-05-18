package kr.co.knowledgerally.local.source

import android.content.SharedPreferences
import kr.co.knowledgerally.data.source.AuthLocalDataSource
import javax.inject.Inject

internal class AuthLocalDataSourceImpl @Inject constructor(
    private val sharedPreferences: SharedPreferences,
) : AuthLocalDataSource {

    override suspend fun getAccessToken(): Result<String> = runCatching {
        sharedPreferences.getString(KEY_ACCESS_TOKEN, "") ?: ""
    }

    companion object {
        private const val KEY_ACCESS_TOKEN = "KEY_ACCESS_TOKEN"
    }
}
