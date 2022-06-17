package kr.co.knowledgerally.ui.ball

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import dagger.hilt.android.AndroidEntryPoint
import kr.co.knowledgerally.base.BaseActivity

@AndroidEntryPoint
class BallBannerActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            BallBannerScreen()
        }
    }

    companion object {
        fun startActivity(context: Context) {
            val intent = Intent(context, BallBannerActivity::class.java)
            context.startActivity(intent)
        }
    }
}