package hs.kr.entrydsm.schedule.domain.schedule.application.port.`in`

import hs.kr.entrydsm.schedule.domain.schedule.adapter.`in`.dto.request.CreateScheduleRequest

interface CreateScheduleUseCase {
    fun execute(request: CreateScheduleRequest)
}