package hs.kr.entrydsm.schedule.domain.schedule.application.service

import hs.kr.entrydsm.schedule.domain.schedule.adapter.`in`.dto.ScheduleDto
import hs.kr.entrydsm.schedule.domain.schedule.adapter.`in`.dto.request.UpdateSchedulesRequest
import hs.kr.entrydsm.schedule.domain.schedule.application.exception.InvalidScheduleRequestException
import hs.kr.entrydsm.schedule.domain.schedule.application.exception.InvalidScheduleSequenceException
import hs.kr.entrydsm.schedule.domain.schedule.application.port.`in`.UpdateSchedulesUseCase
import hs.kr.entrydsm.schedule.domain.schedule.application.port.out.SaveSchedulePort
import hs.kr.entrydsm.schedule.domain.schedule.facade.ScheduleFacade
import hs.kr.entrydsm.schedule.domain.schedule.model.Schedule
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import kotlin.collections.ifEmpty

@Service
class UpdateSchedulesService(
    private val saveSchedulePort: SaveSchedulePort,
    private val scheduleFacade: ScheduleFacade
) : UpdateSchedulesUseCase {
    /**
     * 스케줄을 일괄 업데이트합니다.
     *
     * @param request 업데이트할 스케줄 정보가 포함된 요청 객체
     * @throws InvalidScheduleRequestException 요청이 유효하지 않은 경우
     * @throws InvalidScheduleSequenceException 스케줄 순서가 유효하지 않은 경우
     */
    @Transactional
    override fun execute(request: UpdateSchedulesRequest) {
        request.schedules.ifEmpty {
            throw InvalidScheduleRequestException
        }
        val scheduleDtoList: List<ScheduleDto> = request.schedules
        for (index in scheduleDtoList.indices) {
            val scheduleDto = scheduleDtoList[index]
            val schedule: Schedule = scheduleFacade.getScheduleByType(scheduleDto.type)
            if (index != 0 && scheduleDtoList[index - 1].date.isAfter(scheduleDto.date)) {
                throw InvalidScheduleSequenceException
            }
            saveSchedulePort.saveSchedule(schedule.updateDate(scheduleDto.date))
        }
    }
}