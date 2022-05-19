package kr.co.knowledgerally.base

import android.os.Bundle
import androidx.activity.ComponentActivity

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
