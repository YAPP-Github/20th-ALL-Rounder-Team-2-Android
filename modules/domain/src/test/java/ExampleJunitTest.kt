import com.google.common.truth.Truth
import org.junit.jupiter.api.Test

class ExampleUnitTest {

    @Test
    fun addition_isCorrect() {
        // given & when
        val actual = 2 + 2

        // then
        val expected = 4
        Truth.assertThat(actual).isEqualTo(expected)
    }
}
