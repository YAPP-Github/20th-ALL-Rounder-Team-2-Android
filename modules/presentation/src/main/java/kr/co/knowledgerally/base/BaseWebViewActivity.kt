package kr.co.knowledgerally.base

import kr.co.knowledgerally.bridge.BridgeDelegate

abstract class BaseWebViewActivity : BaseActivity(), BridgeDelegate {

    protected fun navigateUp() = finish()

    companion object {
        const val BASE_URL = "http://knowllydev-web.hkpark.net"
    }
}
