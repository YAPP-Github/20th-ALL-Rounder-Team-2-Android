package kr.co.knowledgerally.domain.usecase

import kr.co.knowledgerally.domain.model.LectureInfo
import kr.co.knowledgerally.domain.repo.LectureRepository
import javax.inject.Inject

class GetPlayerLectureInfoListUseCase @Inject constructor(
    private val repository: LectureRepository,
) {
    // TODO: 데이터 연결
    suspend operator fun invoke(): Result<List<LectureInfo>> = runCatching { emptyList() }
}
