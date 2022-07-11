package kr.co.knowledgerally.domain.repo

import kr.co.knowledgerally.domain.model.LectureInfo
import kr.co.knowledgerally.domain.model.LectureState
import kr.co.knowledgerally.domain.model.Registration
import kr.co.knowledgerally.domain.model.Schedule

interface LectureRepository {

    suspend fun getPlayerLectureInfoList(): Result<List<LectureInfo>>

    suspend fun getCoachLectureInfoList(state: LectureState?): Result<List<LectureInfo>>

    /**
     * @return lectureId: 클래스 정보 등록 이후 반환된 클래스 아이디
     */
    suspend fun register(registration: Registration): Result<Long>

    suspend fun register(lectureId: Long, schedules: List<Schedule>): Result<Unit>
}
