package hs.kr.entrydsm.schedule.model.schedule

import hs.kr.entrydsm.schedule.model.schedule.type.Type
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
    val id: Int,
    val type: Type,
    val date: LocalDateTime
) {
    fun updateDate(newDate: LocalDateTime) = copy(date = newDate)
}
