package hs.kr.entrydsm.schedule.domain.schedule.application.service

import hs.kr.entrydsm.schedule.domain.schedule.adapter.`in`.dto.ScheduleDto
import hs.kr.entrydsm.schedule.domain.schedule.adapter.`in`.dto.request.CreateScheduleRequest
import hs.kr.entrydsm.schedule.domain.schedule.application.port.`in`.CreateScheduleUseCase
import hs.kr.entrydsm.schedule.domain.schedule.application.port.out.SaveSchedulePort
import hs.kr.entrydsm.schedule.domain.schedule.exception.InvalidScheduleRequestException
import hs.kr.entrydsm.schedule.domain.schedule.exception.InvalidScheduleSequenceException
import hs.kr.entrydsm.schedule.domain.schedule.model.Schedule
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class CreateScheduleService(
    private val saveSchedulePort: SaveSchedulePort,
) : CreateScheduleUseCase {

    @Transactional
    override fun execute(request: CreateScheduleRequest) {
        request.schedules.ifEmpty {
            throw InvalidScheduleRequestException
        }

        val scheduleDtoList: List<ScheduleDto> = request.schedules
        for (index in scheduleDtoList.indices) {
            val scheduleDto = scheduleDtoList[index]
            if (index != 0 && scheduleDtoList[index - 1].date.isAfter(scheduleDto.date)) {
                throw InvalidScheduleSequenceException
            }

            saveSchedulePort.saveSchedule(
                Schedule(
                    type = scheduleDto.type,
                    date = scheduleDto.date
                )
            )
        }
    }
}