package hs.kr.entrydsm.schedule.domain.schedule.adapter.`in`.schedule.dto.request

import hs.kr.entrydsm.schedule.domain.schedule.adapter.`in`.schedule.dto.ScheduleDto
import jakarta.validation.constraints.NotNull

/**
 * 스케줄 목록을 업데이트하기 위한 요청 데이터 클래스입니다.
 *
 * @property schedules 업데이트할 스케줄 목록 (null 불가능)
 */
data class UpdateSchedulesRequest(
    @field:NotNull
    val schedules: List<ScheduleDto>
)
