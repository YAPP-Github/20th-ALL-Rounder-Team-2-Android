package kr.co.knowledgerally.domain.usecase

import kr.co.knowledgerally.domain.model.LectureInfo
import kr.co.knowledgerally.domain.model.LectureState
import kr.co.knowledgerally.domain.repo.LectureRepository
import javax.inject.Inject

class GetCoachLectureInfoListUseCase @Inject constructor(
    private val repository: LectureRepository,
) {
    suspend operator fun invoke(state: LectureState?): Result<List<LectureInfo>> =
        repository.getCoachLectureInfoList(state)
}
