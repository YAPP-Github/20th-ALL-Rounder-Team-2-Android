package kr.co.knowledgerally.base

import kr.co.knowledgerally.bridge.WebAppInterface
import javax.inject.Inject

abstract class BaseWebViewActivity : BaseActivity() {

    @Inject
    lateinit var webAppInterface: WebAppInterface

    protected fun navigateUp() = finish()

    companion object {
        const val BASE_URL = "http://knowllydev-web.hkpark.net"
    }
}
