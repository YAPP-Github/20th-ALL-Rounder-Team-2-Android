package kr.co.knowledgerally.domain.usecase

import kr.co.knowledgerally.domain.model.Applicant
import java.time.LocalDate
import javax.inject.Inject

class GetApplicantListUseCase @Inject constructor() {

    suspend operator fun invoke(): Result<List<Applicant>> = runCatching {
        listOf(
            Applicant(
                id = "1",
                name = "유지민",
                content = "프랑스어 강의 신청합니다~",
                imageUrl = null,
                startAt = LocalDate.now()
            ),
            Applicant(
                id = "2",
                name = "유지민",
                content = "프랑스어 배우려고 생각만 했었는데 마침 기회가 생겨서 클래스 신청해 봅니다! ",
                imageUrl = null,
                startAt = LocalDate.now()
            ),
            Applicant(
                id = "3",
                name = "유지민",
                content = "8월부터 교환 학생으로 프랑스에 가는데 잘은 못하더라도 간단한 회화 정도는 하고 싶어져서 신청했어요. 기초도 모르는 상태라 어쩔지 모르겠습니다.",
                imageUrl = null,
                startAt = LocalDate.now(),
            ),
            Applicant(
                id = "4",
                name = "유지민",
                content = "8월부터 교환 학생으로 프랑스에 가는데 잘은 못하더라도 간단한 회화 정도는 하고 싶어져서 신청했어요. 기초도 모르는 상태라 어쩔지 모르겠습니다.",
                imageUrl = null,
                startAt = LocalDate.now(),
            ),
            Applicant(
                id = "5",
                name = "유지민",
                content = "8월부터 교환 학생으로 프랑스에 가는데 잘은 못하더라도 간단한 회화 정도는 하고 싶어져서 신청했어요. 기초도 모르는 상태라 어쩔지 모르겠습니다.",
                imageUrl = null,
                startAt = LocalDate.now(),
            ),
        )
    }
}
