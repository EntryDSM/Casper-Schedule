package hs.kr.entrydsm.schedule.domain.schedule.facade

import hs.kr.entrydsm.schedule.domain.schedule.domain.Schedule
import hs.kr.entrydsm.schedule.domain.schedule.domain.repository.ScheduleRepository
import hs.kr.entrydsm.schedule.domain.schedule.domain.types.Type
import hs.kr.entrydsm.schedule.domain.schedule.exception.ScheduleNotFoundException
import org.springframework.stereotype.Component

@Component
class ScheduleFacade(
    val scheduleRepository: ScheduleRepository
) {
    fun getScheduleByType(type: Type): Schedule =
        scheduleRepository.findByType(type) ?: throw ScheduleNotFoundException
}