package kr.co.knowledgerally.local.source

import android.content.SharedPreferences
import androidx.core.content.edit
import kr.co.knowledgerally.data.source.AppMetaLocalDataSource
import javax.inject.Inject

internal class AppMetaLocalDataSourceImpl @Inject constructor(
    private val sharedPreferences: SharedPreferences,
) : AppMetaLocalDataSource {

    override suspend fun isWelcomeShown(): Result<Boolean> = runCatching {
        sharedPreferences.getBoolean(KEY_IS_WELCOME_SHOWN, false)
    }

    override suspend fun shownWelcome(): Result<Unit> = runCatching {
        sharedPreferences.edit {
            putBoolean(KEY_IS_WELCOME_SHOWN, true)
        }
    }

    companion object {
        private const val KEY_IS_WELCOME_SHOWN = "KEY_IS_WELCOME_SHOWN"
    }
}
