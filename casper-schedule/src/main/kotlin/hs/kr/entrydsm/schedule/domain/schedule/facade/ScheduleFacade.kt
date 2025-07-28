package hs.kr.entrydsm.schedule.domain.schedule.facade

import hs.kr.entrydsm.schedule.domain.schedule.application.exception.ScheduleNotFoundException
import hs.kr.entrydsm.schedule.domain.schedule.application.port.`in`.ScheduleFacadeUseCase
import hs.kr.entrydsm.schedule.domain.schedule.application.port.out.FindSchedulePort
import hs.kr.entrydsm.schedule.domain.schedule.model.Schedule
import hs.kr.entrydsm.schedule.domain.schedule.model.type.Type
import org.springframework.stereotype.Component

/**
 * 스케줄 관련 퍼사드 클래스입니다.
 * 애플리케이션의 외부 레이어와 도메인 로직 사이의 중재자 역할을 하며,
 * 복잡한 도메인 로직을 캡슐화하여 간단한 인터페이스를 제공합니다.
 *
 * @property findSchedulePort 스케줄 조회를 위한 포트 인터페이스
 */
@Component
class ScheduleFacade(
    private val findSchedulePort: FindSchedulePort
) : ScheduleFacadeUseCase {
    override fun getScheduleByType(type: Type): Schedule = findSchedulePort.findByType(type)
        ?: throw ScheduleNotFoundException
}
