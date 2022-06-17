package kr.co.knowledgerally.ui.applicant

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import dagger.hilt.android.AndroidEntryPoint
import kr.co.knowledgerally.base.BaseActivity
import kr.co.knowledgerally.ui.theme.KnowllyTheme

@AndroidEntryPoint
class ApplicantActivity : BaseActivity() {

    private val viewModel: ApplicantViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            KnowllyTheme {
                ApplicantScreen(
                    viewModel = viewModel,
                    navigateUp = ::navigateUp,
                    navigateToForm = { },
                )
            }
        }
    }

    private fun navigateUp() = finish()

    companion object {
        fun getIntent(context: Context, classId: String): Intent =
            Intent(context, ApplicantActivity::class.java)
                .putExtra(ApplicantViewModel.KEY_CLASS_ID, classId)
    }
}
