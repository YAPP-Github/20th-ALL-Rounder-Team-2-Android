package kr.co.knowledgerally.ui.ball

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import dagger.hilt.android.AndroidEntryPoint
import kr.co.knowledgerally.base.BaseActivity

@AndroidEntryPoint
class BallGuideActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            BallGuideScreen(navigateUp = ::navigateUp)
        }
    }

    private fun navigateUp() = finish()

    companion object {
        fun startActivity(context: Context) {
            val intent = Intent(context, BallGuideActivity::class.java)
            context.startActivity(intent)
        }
    }
}