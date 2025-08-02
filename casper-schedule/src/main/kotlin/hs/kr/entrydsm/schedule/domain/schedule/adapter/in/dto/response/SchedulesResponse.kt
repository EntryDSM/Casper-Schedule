package hs.kr.entrydsm.schedule.domain.schedule.adapter.`in`.dto.response

import hs.kr.entrydsm.schedule.domain.schedule.adapter.`in`.dto.ScheduleDto

/**
 * 스케줄 목록과 현재 상태를 포함하는 응답 데이터 클래스입니다.
 *
 * @property schedules 스케줄 정보 목록 (null 가능한 ScheduleDto 리스트)
 * @property currentStatus 현재 스케줄 상태를 나타내는 문자열
 */
data class SchedulesResponse(
    val schedules: List<ScheduleDto>,
    val currentStatus: String
)
