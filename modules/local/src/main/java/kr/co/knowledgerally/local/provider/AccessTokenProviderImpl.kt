package kr.co.knowledgerally.local.provider

import android.content.SharedPreferences
import kr.co.knowledgerally.data.provider.AccessTokenProvider
import javax.inject.Inject

internal class AccessTokenProviderImpl @Inject constructor(
    private val sharedPreferences: SharedPreferences
) : AccessTokenProvider {

    override fun get(): String = sharedPreferences.getString(KEY_ACCESS_TOKEN, null) ?: ""

    companion object {
        private const val KEY_ACCESS_TOKEN = "KEY_ACCESS_TOKEN"
    }
}
