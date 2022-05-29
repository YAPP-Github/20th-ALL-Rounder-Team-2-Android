package kr.co.knowledgerally.ui.profile

import android.content.Context
import android.content.Intent
import dagger.hilt.android.AndroidEntryPoint
import kr.co.knowledgerally.base.BaseActivity

@AndroidEntryPoint
class ProfileActivity : BaseActivity() {

    companion object {
        fun startActivity(context: Context) {
            val intent = Intent(context, ProfileActivity::class.java)
            context.startActivity(intent)
        }
    }
}
