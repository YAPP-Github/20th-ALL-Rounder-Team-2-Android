package kr.co.knowledgerally.domain.model

data class LectureBundle(
    val onboardingLectures: List<LectureLegacy.Onboard>,
    val ongoingLectures: List<LectureLegacy.Ongoing>,
    val doneLectures: List<LectureLegacy.Done>
)
