package kr.co.knowledgerally.ui.profile

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import dagger.hilt.android.AndroidEntryPoint
import kr.co.knowledgerally.base.BaseActivity
import kr.co.knowledgerally.ui.theme.KnowllyTheme

@AndroidEntryPoint
class ProfileActivity : BaseActivity() {

    private val viewModel: ProfileViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            KnowllyTheme {
                ProfileScreen(viewModel = viewModel)
            }
        }
    }

    companion object {
        fun startActivity(context: Context) {
            val intent = Intent(context, ProfileActivity::class.java)
            context.startActivity(intent)
        }
    }
}
