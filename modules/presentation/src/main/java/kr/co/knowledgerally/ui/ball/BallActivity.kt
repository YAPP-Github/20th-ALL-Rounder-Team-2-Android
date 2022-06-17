package kr.co.knowledgerally.ui.ball

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import dagger.hilt.android.AndroidEntryPoint
import kr.co.knowledgerally.base.BaseActivity
import kr.co.knowledgerally.ui.theme.KnowllyTheme

@AndroidEntryPoint
class BallActivity : BaseActivity() {

    private val viewModel: BallViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            KnowllyTheme {
                BallScreen(
                    viewModel = viewModel,
                    navigateUp = ::navigateUp,
                    navigateToGuide = ::startBallGuideActivity
                )
            }
        }
    }

    private fun navigateUp() = finish()

    private fun startBallGuideActivity() = BallGuideActivity.startActivity(this)

    companion object {
        fun startActivity(context: Context) {
            val intent = Intent(context, BallActivity::class.java)
            context.startActivity(intent)
        }
    }
}
