package kr.co.knowledgerally.domain.usecase

import kr.co.knowledgerally.domain.model.Registration
import kr.co.knowledgerally.domain.repo.LectureRepository
import javax.inject.Inject

class RegisterLectureInfoUseCase @Inject constructor(
    private val repository: LectureRepository
) {

    /**
     * @return lectureId
     */
    suspend operator fun invoke(registration: Registration): Result<Long> =
        repository.register(registration)
}
