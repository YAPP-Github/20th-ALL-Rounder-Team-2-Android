package kr.co.knowledgerally.domain.model

data class LectureBundle(
    val onboardingLectures: List<Lecture.Onboard>,
    val ongoingLectures: List<Lecture.Ongoing>,
    val doneLectures: List<Lecture.Done>
) {
    val isEmpty =
        onboardingLectures.isEmpty() and ongoingLectures.isEmpty() and doneLectures.isEmpty()
}
