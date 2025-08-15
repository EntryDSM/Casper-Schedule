package hs.kr.entrydsm.schedule.domain.schedule.application.port.`in`

import hs.kr.entrydsm.schedule.domain.schedule.model.Schedule
import hs.kr.entrydsm.schedule.domain.schedule.model.type.Type

/**
 * 외부 시스템에서 사용할 일정 관련 유스케이스 인터페이스입니다.
 */
interface ScheduleFacadeUseCase {
    /**
     * 지정된 타입의 일정을 조회합니다.
     *
     * @param type 조회할 일정의 타입
     * @return 조회된 일정 (없을 경우 예외 발생)
     * @throws ScheduleNotFoundException 해당 타입의 일정을 찾을 수 없는 경우
     */
    fun getScheduleByType(type: Type): Schedule
}
