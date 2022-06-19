package kr.co.knowledgerally

import android.app.Application
import com.kakao.sdk.common.KakaoSdk
import dagger.hilt.android.HiltAndroidApp
import kr.co.knowledgerally.log.AndroidLogger
import kr.co.knowledgerally.log.Logger
import kr.co.knowledgerally.toast.AndroidToast
import kr.co.knowledgerally.toast.Toaster

@HiltAndroidApp
class App : Application() {

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Logger.add(AndroidLogger)
        }

        Toaster.init(AndroidToast(this))
        KakaoSdk.init(this, BuildConfig.KAKAO_API_KEY)
    }
}
