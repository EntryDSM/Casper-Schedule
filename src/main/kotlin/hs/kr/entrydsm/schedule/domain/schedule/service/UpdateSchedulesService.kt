package hs.kr.entrydsm.schedule.domain.schedule.service

import hs.kr.entrydsm.schedule.domain.schedule.domain.Schedule
import hs.kr.entrydsm.schedule.domain.schedule.exception.InvalidScheduleRequestException
import hs.kr.entrydsm.schedule.domain.schedule.exception.InvalidScheduleSequenceException
import hs.kr.entrydsm.schedule.domain.schedule.facade.ScheduleFacade
import hs.kr.entrydsm.schedule.domain.schedule.presentation.dto.ScheduleDto
import hs.kr.entrydsm.schedule.domain.schedule.presentation.dto.request.UpdateSchedulesRequest
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UpdateSchedulesService(
    private val scheduleFacade: ScheduleFacade
) {
    @Transactional
    fun execute(request: UpdateSchedulesRequest) {
        request.schedules.ifEmpty {
            throw InvalidScheduleRequestException
        }
        val scheduleDtoList: List<ScheduleDto> = request.schedules // todo 로직 변경 꼭 필요!!
        for (index in scheduleDtoList.indices) {
            val scheduleDto = scheduleDtoList[index]
            val schedule: Schedule = scheduleFacade.getScheduleByType(scheduleDto.type)
            if (index != 0 && scheduleDtoList[index - 1].date.isAfter(scheduleDto.date)) {
                throw InvalidScheduleSequenceException
            }
            schedule.updateDate(scheduleDto.date)
        }
    }
}