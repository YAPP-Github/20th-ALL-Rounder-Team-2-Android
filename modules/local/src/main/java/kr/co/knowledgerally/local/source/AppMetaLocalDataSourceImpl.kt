package kr.co.knowledgerally.local.source

import android.content.SharedPreferences
import kr.co.knowledgerally.data.source.AppMetaLocalDataSource
import javax.inject.Inject

internal class AppMetaLocalDataSourceImpl @Inject constructor(
    private val sharedPreferences: SharedPreferences,
) : AppMetaLocalDataSource {

    override suspend fun isWelcomeShown(): Result<Boolean> = runCatching {
        sharedPreferences.getBoolean(KEY_IS_WELCOME_SHOWN, false)
    }

    companion object {
        private const val KEY_IS_WELCOME_SHOWN = "KEY_IS_WELCOME_SHOWN"
    }
}
