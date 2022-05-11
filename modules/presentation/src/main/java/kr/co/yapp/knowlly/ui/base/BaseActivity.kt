package kr.co.yapp.knowlly.ui.base

import android.os.Bundle
import androidx.activity.ComponentActivity
import kr.co.yapp.knowlly.ui.transition.ActivityTransition

abstract class BaseActivity : ComponentActivity() {

    protected inline val TAG get() = this::class.java.simpleName
    protected open val activityTransition = ActivityTransition.Push

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityTransition.overridePendingTransition(this)
    }

    override fun finish() {
        super.finish()
        activityTransition.overridePendingPopTransition(this)
    }
}
