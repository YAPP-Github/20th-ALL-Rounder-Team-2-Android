package kr.co.yapp.knowlly

import com.google.common.truth.Truth.assertThat
import org.junit.jupiter.api.Test

class ExampleUnitTest {

    @Test
    fun addition_isCorrect() {
        // given & when
        val actual = 2 + 2

        // then
        val expected = 4
        assertThat(actual).isEqualTo(expected)
    }
}
