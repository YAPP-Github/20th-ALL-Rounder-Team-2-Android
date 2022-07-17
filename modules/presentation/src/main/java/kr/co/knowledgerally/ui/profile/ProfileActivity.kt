package kr.co.knowledgerally.ui.profile

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import kr.co.knowledgerally.base.BaseActivity
import kr.co.knowledgerally.ui.main.MainActivity
import kr.co.knowledgerally.ui.profile.state.CompleteState
import kr.co.knowledgerally.ui.profile.state.Mode
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

        lifecycleScope.launch {
            viewModel.completed.collect { complete ->
                when (complete) {
                    CompleteState.Created -> startMainActivity()
                    CompleteState.Modified -> {
                        viewModel.refreshUser()
                        finish()
                    }
                    CompleteState.Waiting -> {}
                }
            }
        }
    }

    private fun startMainActivity() {
        MainActivity.startActivity(this)
        finish()
    }

    companion object {
        fun getIntent(context: Context, mode: Mode) =
            Intent(context, ProfileActivity::class.java)
                .putExtra(ProfileViewModel.KEY_MODE, mode)
    }
}
