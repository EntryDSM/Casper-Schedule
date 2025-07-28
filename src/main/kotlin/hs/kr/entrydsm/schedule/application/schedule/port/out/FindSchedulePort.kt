package hs.kr.entrydsm.schedule.application.schedule.port.out

import hs.kr.entrydsm.schedule.model.schedule.Schedule
import hs.kr.entrydsm.schedule.model.schedule.type.Type

/**
 * 일정을 조회하는 포트 인터페이스입니다.
 */
interface FindSchedulePort {
    /**
     * 지정된 타입의 일정을 조회합니다.
     *
     * @param type 조회할 일정의 타입
     * @return 일치하는 일정 (없을 경우 null)
     */
    fun findByType(type: Type): Schedule?

    /**
     * 모든 일정을 조회합니다.
     *
     * @return 모든 일정 목록 (없을 경우 빈 리스트)
     */
    fun findAllBy(): List<Schedule>
}
