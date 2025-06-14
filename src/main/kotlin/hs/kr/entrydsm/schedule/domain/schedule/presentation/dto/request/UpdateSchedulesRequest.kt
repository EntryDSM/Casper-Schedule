package hs.kr.entrydsm.schedule.domain.schedule.presentation.dto.request

import hs.kr.entrydsm.schedule.domain.schedule.presentation.dto.ScheduleDto
import jakarta.validation.constraints.NotNull

data class UpdateSchedulesRequest(

    @NotNull
    val schedules: List<ScheduleDto>
)