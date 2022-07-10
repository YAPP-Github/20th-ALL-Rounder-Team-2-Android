package kr.co.knowledgerally

import android.app.Application
import com.facebook.spectrum.SpectrumSoLoader
import com.facebook.stetho.Stetho
import com.google.firebase.crashlytics.FirebaseCrashlytics
import com.kakao.sdk.common.KakaoSdk
import dagger.hilt.android.HiltAndroidApp
import kr.co.knowledgerally.log.AndroidLogger
import kr.co.knowledgerally.log.CrashlyticsLogger
import kr.co.knowledgerally.log.Logger
import kr.co.knowledgerally.toast.AndroidToast
import kr.co.knowledgerally.toast.Toaster

@HiltAndroidApp
class App : Application() {

    override fun onCreate() {
        super.onCreate()

        // Logger
        if (BuildConfig.DEBUG) {
            Logger.add(AndroidLogger)
        } else {
            Logger.add(CrashlyticsLogger)
        }

        // Toaster
        Toaster.init(AndroidToast(this))

        // Kakao
        KakaoSdk.init(this, BuildConfig.KAKAO_API_KEY)

        // Spectrum
        SpectrumSoLoader.init(this)

        // Firebase
        FirebaseCrashlytics
            .getInstance()
            .setCrashlyticsCollectionEnabled(!BuildConfig.DEBUG)

        // Stetho
        if (BuildConfig.DEBUG) {
            Stetho.initializeWithDefaults(this)
        }
    }
}
