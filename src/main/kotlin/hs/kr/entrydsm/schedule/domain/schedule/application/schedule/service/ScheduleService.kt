package hs.kr.entrydsm.schedule.application.schedule.service

import hs.kr.entrydsm.schedule.adapter.`in`.schedule.dto.ScheduleDto
import hs.kr.entrydsm.schedule.adapter.`in`.schedule.dto.request.UpdateSchedulesRequest
import hs.kr.entrydsm.schedule.adapter.`in`.schedule.dto.response.SchedulesResponse
import hs.kr.entrydsm.schedule.application.schedule.exception.InvalidScheduleRequestException
import hs.kr.entrydsm.schedule.application.schedule.exception.InvalidScheduleSequenceException
import hs.kr.entrydsm.schedule.application.schedule.port.`in`.ScheduleUseCase
import hs.kr.entrydsm.schedule.application.schedule.port.out.FindSchedulePort
import hs.kr.entrydsm.schedule.application.schedule.port.out.SaveSchedulePort
import hs.kr.entrydsm.schedule.facade.ScheduleFacade
import hs.kr.entrydsm.schedule.model.schedule.Schedule
import hs.kr.entrydsm.schedule.model.schedule.type.Type
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

/**
 * 스케줄 관련 비즈니스 로직을 처리하는 서비스 클래스입니다.
 *
 * @property scheduleFacade 스케줄 관련 퍼사드 인터페이스
 * @property findSchedulePort 스케줄 조회 포트 인터페이스
 * @property saveSchedulePort 스케줄 저장 포트 인터페이스
 */
@Service
class ScheduleService(
    private val scheduleFacade: ScheduleFacade,
    private val findSchedulePort: FindSchedulePort,
    private val saveSchedulePort: SaveSchedulePort
) : ScheduleUseCase {
    /**
     * 특정 타입의 스케줄을 조회합니다.
     *
     * @param type 조회할 스케줄 타입 문자열
     * @return 조회된 스케줄 정보가 포함된 ScheduleDto 객체
     */
    override fun queryScheduleByType(type: String): ScheduleDto {
        val schedule = scheduleFacade.getScheduleByType(Type.valueOf(type))
        return ScheduleDto(schedule.type, schedule.date)
    }

    /**
     * 모든 스케줄을 조회합니다.
     *
     * @return 모든 스케줄 정보와 현재 상태가 포함된 SchedulesResponse 객체
     */
    override fun querySchedules(): SchedulesResponse {
        return SchedulesResponse(
            schedules = (
                findSchedulePort.findAllBy()
                    .map { schedule -> ScheduleDto(schedule.type, schedule.date) }
                ),
            currentStatus = getCurrentStatus()
        )
    }

    /**
     * 현재 시점의 스케줄 상태를 반환합니다.
     *
     * @return 현재 시점의 스케줄 상태를 나타내는 문자열
     * @throws InvalidScheduleSequenceException 스케줄 순서가 유효하지 않은 경우
     */
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

    /**
     * 스케줄을 일괄 업데이트합니다.
     *
     * @param request 업데이트할 스케줄 정보가 포함된 요청 객체
     * @throws InvalidScheduleRequestException 요청이 유효하지 않은 경우
     * @throws InvalidScheduleSequenceException 스케줄 순서가 유효하지 않은 경우
     */
    @Transactional
    override fun updateSchedules(request: UpdateSchedulesRequest) {
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
