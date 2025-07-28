package hs.kr.entrydsm.schedule.domain.schedule.application.service

import hs.kr.entrydsm.schedule.domain.schedule.adapter.`in`.dto.ScheduleDto
import hs.kr.entrydsm.schedule.domain.schedule.adapter.`in`.dto.response.SchedulesResponse
import hs.kr.entrydsm.schedule.domain.schedule.application.exception.InvalidScheduleSequenceException
import hs.kr.entrydsm.schedule.domain.schedule.application.port.`in`.QuerySchedulesUseCase
import hs.kr.entrydsm.schedule.domain.schedule.application.port.out.FindSchedulePort
import hs.kr.entrydsm.schedule.domain.schedule.facade.ScheduleFacade
import hs.kr.entrydsm.schedule.domain.schedule.model.Schedule
import hs.kr.entrydsm.schedule.domain.schedule.model.type.Type
import org.springframework.stereotype.Service
import java.time.LocalDateTime

/**
 * 모든 스케줄을 조회하는 서비스 구현체입니다.
 * 저장소로부터 스케줄 정보를 조회하고, 현재 상태 정보와 함께 반환합니다.
 *
 * @property findSchedulePort 스케줄 조회를 위한 포트
 * @property scheduleFacade 스케줄 관련 비즈니스 로직을 처리하는 퍼사드
 */
@Service
class QuerySchedulesService(
    private val findSchedulePort: FindSchedulePort,
    private val scheduleFacade: ScheduleFacade
) : QuerySchedulesUseCase {
    /**
     * 모든 스케줄을 조회합니다.
     *
     * @return 모든 스케줄 정보와 현재 상태가 포함된 SchedulesResponse 객체
     */
    override fun execute(): SchedulesResponse {
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
}