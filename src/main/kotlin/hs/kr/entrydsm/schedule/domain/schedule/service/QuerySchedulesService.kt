package hs.kr.entrydsm.schedule.domain.schedule.service

import hs.kr.entrydsm.schedule.domain.schedule.domain.Schedule
import hs.kr.entrydsm.schedule.domain.schedule.domain.repository.ScheduleRepository
import hs.kr.entrydsm.schedule.domain.schedule.domain.types.Type
import hs.kr.entrydsm.schedule.domain.schedule.exception.InvalidScheduleSequenceException
import hs.kr.entrydsm.schedule.domain.schedule.facade.ScheduleFacade
import hs.kr.entrydsm.schedule.domain.schedule.presentation.dto.ScheduleDto
import hs.kr.entrydsm.schedule.domain.schedule.presentation.dto.response.SchedulesResponse
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class QuerySchedulesService(
    private val scheduleRepository: ScheduleRepository,
    private val scheduleFacade: ScheduleFacade
) {
    fun execute(): SchedulesResponse {
        return SchedulesResponse(
            schedules = (
                    scheduleRepository.findAllBy()
                        .map { schedule -> ScheduleDto(schedule.type, schedule.date) }
                    ),
            currentStatus = getCurrentStatus()
        )
    }

    private fun getCurrentStatus(): String {
        val now = LocalDateTime.now()
        val firstAnnounce: Schedule = scheduleFacade.getScheduleByType(Type.FIRST_ANNOUNCEMENT)
        val interview: Schedule = scheduleFacade.getScheduleByType(Type.INTERVIEW)
        val secondAnnounce: Schedule = scheduleFacade.getScheduleByType(Type.SECOND_ANNOUNCEMENT)

        val startDate = scheduleFacade.getScheduleByType(Type.START_DATE).date
        val endDate = scheduleFacade.getScheduleByType(Type.END_DATE).date

        return when {
            now.isBefore(startDate) -> "NOT_APPLICATION_PERIOD"
            now.isAfter(startDate) && now.isBefore(endDate) -> "APPLICATION_PERIOD"
            now.isAfter(endDate) && now.isBefore(firstAnnounce.date) -> "BEFORE_FIRST_ANNOUNCEMENT"
            now.isEqual(firstAnnounce.date) -> firstAnnounce.type.toString()
            now.isBefore(interview.date) -> "BEFORE_INTERVIEW"
            now.isEqual(interview.date) -> interview.type.toString()
            now.isBefore(secondAnnounce.date) -> "BEFORE_SECOND_ANNOUNCEMENT"
            now.isEqual(secondAnnounce.date) -> secondAnnounce.type.toString()
            now.isAfter(secondAnnounce.date) -> "END"
            else -> throw InvalidScheduleSequenceException
        }
    }
}