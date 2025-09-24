package hs.kr.entrydsm.schedule.domain.schedule.adapter.`in`.dto.request

import hs.kr.entrydsm.schedule.domain.schedule.adapter.`in`.dto.ScheduleDto
import jakarta.validation.constraints.NotNull

data class CreateScheduleRequest(
    @field:NotNull
    val schedules: List<ScheduleDto>
)
