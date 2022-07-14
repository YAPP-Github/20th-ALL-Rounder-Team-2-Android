package kr.co.knowledgerally.base

import kr.co.knowledgerally.bridge.WebAppInterface
import javax.inject.Inject

abstract class BaseWebViewActivity : BaseActivity() {

    @Inject
    lateinit var webAppInterface: WebAppInterface

    protected fun navigateUp() = finish()

    companion object {
        const val DOMAIN = "knowllydev-web.hkpark.net"
    }
}
