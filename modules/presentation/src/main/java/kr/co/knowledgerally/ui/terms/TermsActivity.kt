package kr.co.knowledgerally.ui.terms

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import kr.co.knowledgerally.base.BaseActivity
import kr.co.knowledgerally.ui.theme.KnowllyTheme

class TermsActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            KnowllyTheme {
                TermsScreen()
            }
        }
    }

    companion object {

        fun startActivity(context: Context) {
            val intent = Intent(context, TermsActivity::class.java)
            context.startActivity(intent)
        }
    }
}
