package kr.co.knowledgerally.local.provider

import android.content.SharedPreferences
import androidx.core.content.edit
import kr.co.knowledgerally.data.provider.AccessTokenProvider
import javax.inject.Inject

internal class AccessTokenProviderImpl @Inject constructor(
    private val sharedPreferences: SharedPreferences
) : AccessTokenProvider {

    override var value: String
        get() = sharedPreferences.getString(KEY_ACCESS_TOKEN, null) ?: ""
        set(value) {
            sharedPreferences.edit(true) {
                putString(KEY_ACCESS_TOKEN, value)
            }
        }

    companion object {
        private const val KEY_ACCESS_TOKEN = "KEY_ACCESS_TOKEN"
    }
}
