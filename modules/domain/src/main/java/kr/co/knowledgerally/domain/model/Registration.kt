package kr.co.knowledgerally.domain.model

/**
 * 클래스 등록 정보
 */
data class Registration(
    /**
     * 클래스 카테고리
     */
    val category: Category,
    /**
     * 클래스 주제
     */
    val topic: String,
    /**
     * 클래스 소개
     */
    val introduce: String,
    /**
     * 클래스 태그
     */
    val tags: List<String>,
    /**
     * 클래스 이미지
     */
    val imageUris: List<String>,
)
