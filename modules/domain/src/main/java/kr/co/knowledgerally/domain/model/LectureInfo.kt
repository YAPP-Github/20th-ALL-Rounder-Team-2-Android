package kr.co.knowledgerally.domain.model

data class LectureInfo(
    /**
     * 클래스 아이디
     */
    val id: Long,
    /**
     * 강의(일정) 목록
     */
    val lectures: List<Lecture>,
    /**
     * 클래스 제목
     */
    val topic: String,
    /**
     * 클래스 사진 목록
     */
    val imageUrls: List<String>,
    /**
     * 클래스 주최한 코치
     */
    val coach: User,
) {
    /**
     * 클래스 목록으로 보여주는 썸네일 이미지
     */
    val thumbnailImageUrl: String? get() = imageUrls.firstOrNull()
}
