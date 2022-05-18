package kr.co.knowledgerally.domain.usecase

import com.google.common.truth.Truth.assertThat
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import kr.co.knowledgerally.domain.repo.AuthRepository
import org.junit.jupiter.api.Test
import kotlin.Result.Companion.success

class IsLoggedInUseCaseTest {

    private val authRepository: AuthRepository = mockk()

    @Test
    fun `토큰이 있으면 true를 반환해야 한다`() = runTest {
        // given
        coEvery { authRepository.getAccessToken() } returns success("Access-Token")
        val useCase = IsLoggedInUseCase(authRepository)

        // when
        val actual = useCase().getOrThrow()

        // then
        assertThat(actual).isTrue()
    }

    @Test
    fun `토큰이 없으면 false를 반환해야 한다`() = runTest {
        // given
        coEvery { authRepository.getAccessToken() } returns success("")
        val useCase = IsLoggedInUseCase(authRepository)

        // when
        val actual = useCase().getOrThrow()

        // then
        assertThat(actual).isFalse()
    }
}
