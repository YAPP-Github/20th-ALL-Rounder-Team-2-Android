package kr.co.knowledgerally.base

import kr.co.knowledgerally.bridge.BridgeDelegate

abstract class BaseWebViewActivity : BaseActivity(), BridgeDelegate {

    protected fun navigateUp() = finish()
}
