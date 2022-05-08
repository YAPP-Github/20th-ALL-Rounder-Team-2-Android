package kr.co.yapp.knowlly

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import kr.co.yapp.knowlly.log.AndroidLogger
import kr.co.yapp.knowlly.log.Logger

@HiltAndroidApp
class App : Application() {

    override fun onCreate() {
        super.onCreate()

        Logger.add(AndroidLogger)
    }
}
