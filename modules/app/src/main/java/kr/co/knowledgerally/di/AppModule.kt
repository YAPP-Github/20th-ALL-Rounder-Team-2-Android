package kr.co.knowledgerally.di

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import com.facebook.stetho.okhttp3.StethoInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kr.co.knowledgerally.BuildConfig
import kr.co.knowledgerally.domain.model.VersionName
import kr.co.knowledgerally.log.Logger
import kr.co.knowledgerally.remote.api.AuthenticationListener
import kr.co.knowledgerally.remote.api.BaseUrl
import kr.co.knowledgerally.remote.api.Interceptors
import kr.co.knowledgerally.ui.splash.SplashActivity
import okhttp3.logging.HttpLoggingInterceptor

@Module
@InstallIn(SingletonComponent::class)
internal object AppModule {

    @Provides
    fun provideBaseUrl(): BaseUrl = if (BuildConfig.DEBUG) {
        BaseUrl("http://knowllydev.hkpark.net/api/")
    } else {
        BaseUrl("https://knowlly.hkpark.net/")
    }

    @Provides
    fun provideInterceptors(): Interceptors {
        return if (BuildConfig.DEBUG) {
            val logger = HttpLoggingInterceptor.Logger { message ->
                Logger.d("OkHttp", message)
            }
            val loggingInterceptor = HttpLoggingInterceptor(logger)
                .apply { level = HttpLoggingInterceptor.Level.BODY }
            Interceptors(
                listOf(loggingInterceptor),
                listOf(StethoInterceptor())
            )
        } else {
            Interceptors.Empty
        }
    }

    @Provides
    fun provideAuthenticationListener(
        @ApplicationContext context: Context,
        sharedPreferences: SharedPreferences,
    ): AuthenticationListener = object : AuthenticationListener {
        override fun onSessionExpired() {
            sharedPreferences.edit { clear() }
            SplashActivity.startActivity(context)
        }
    }

    @Provides
    fun provideVersionName() = VersionName(BuildConfig.VERSION_NAME)
}
