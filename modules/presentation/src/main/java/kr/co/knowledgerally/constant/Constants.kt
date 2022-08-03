package kr.co.knowledgerally.constant

import kr.co.knowledgerally.ui.BuildConfig

val BASE_URL = if (BuildConfig.DEBUG) {
    "http://knowllydev-web.hkpark.net"
} else {
    "https://knowlly-web.hkpark.net/"
}
