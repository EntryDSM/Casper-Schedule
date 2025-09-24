package hs.kr.entrydsm.schedule.domain.schedule.model

import hs.kr.entrydsm.schedule.domain.schedule.model.type.Type
import java.time.LocalDateTime

/**
 * 스케줄 도메인 모델 클래스입니다.
 * 시스템에서 사용되는 스케줄 정보를 나타냅니다.
 *
 * @property id 스케줄 고유 식별자
 * @property type 스케줄 유형 (START_DATE, FIRST_ANNOUNCEMENT, INTERVIEW, SECOND_ANNOUNCEMENT, END_DATE)
 * @property date 스케줄 날짜 및 시간
 */
data class Schedule(
    val id: Int? = null,
    val type: Type,
    val date: LocalDateTime
) {
    /**
     * 스케줄의 날짜를 업데이트한 새로운 [Schedule] 인스턴스를 반환합니다.
     * 원본 객체는 불변(immutable)이므로, 이 메서드는 기존 객체를 수정하지 않고
     * 새로운 객체를 생성하여 반환합니다.
     *
     * @param newDate 새로 설정할 날짜 및 시간
     * @return 날짜가 업데이트된 새로운 Schedule 인스턴스
     */
    fun updateDate(newDate: LocalDateTime) = copy(date = newDate)
}
