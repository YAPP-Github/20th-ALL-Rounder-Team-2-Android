package kr.co.knowledgerally.local.provider

import android.content.SharedPreferences
import androidx.core.content.edit
import kr.co.knowledgerally.data.provider.RefreshTokenProvider
import javax.inject.Inject

internal class RefreshTokenProviderImpl @Inject constructor(
    private val sharedPreferences: SharedPreferences
) : RefreshTokenProvider {

    override var value: String
        get() = sharedPreferences.getString(KEY_REFRESH_TOKEN, null) ?: ""
        set(value) {
            sharedPreferences.edit {
                putString(KEY_REFRESH_TOKEN, value)
            }
        }

    companion object {
        private const val KEY_REFRESH_TOKEN = "KEY_REFRESH_TOKEN"
    }
}
