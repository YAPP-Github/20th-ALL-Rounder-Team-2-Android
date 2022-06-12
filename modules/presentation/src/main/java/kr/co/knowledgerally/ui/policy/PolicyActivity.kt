package kr.co.knowledgerally.ui.policy

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import kr.co.knowledgerally.base.BaseActivity
import kr.co.knowledgerally.ui.theme.KnowllyTheme

class PolicyActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            KnowllyTheme {
                PolicyScreen()
            }
        }
    }

    companion object {

        fun startActivity(context: Context) {
            val intent = Intent(context, PolicyActivity::class.java)
            context.startActivity(intent)
        }
    }
}
