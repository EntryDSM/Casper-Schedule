package hs.kr.entrydsm.schedule.domain.schedule.application.service

import hs.kr.entrydsm.schedule.domain.schedule.adapter.`in`.dto.ScheduleDto
import hs.kr.entrydsm.schedule.domain.schedule.application.port.`in`.QueryScheduleByTypeUseCase
import hs.kr.entrydsm.schedule.domain.schedule.facade.ScheduleFacade
import hs.kr.entrydsm.schedule.domain.schedule.model.type.Type
import org.springframework.stereotype.Service

@Service
class QueryScheduleByTypeService(
    private val scheduleFacade: ScheduleFacade
) : QueryScheduleByTypeUseCase {
    /**
     * 특정 타입의 스케줄을 조회합니다.
     *
     * @param type 조회할 스케줄 타입 문자열
     * @return 조회된 스케줄 정보가 포함된 ScheduleDto 객체
     */
    override fun execute(type: String): ScheduleDto {
        val schedule = scheduleFacade.getScheduleByType(Type.valueOf(type))
        return ScheduleDto(schedule.type, schedule.date)
    }
}