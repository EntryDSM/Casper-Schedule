package hs.kr.entrydsm.schedule.domain.schedule.application.port.`in`

import hs.kr.entrydsm.schedule.domain.schedule.adapter.`in`.schedule.dto.ScheduleDto

interface QueryScheduleByTypeUseCase {
    /**
     * 지정된 타입의 일정을 조회합니다.
     *
     * @param type 조회할 일정의 타입 (문자열)
     * @return 조회된 일정 DTO
     * @throws IllegalArgumentException 유효하지 않은 타입인 경우
     */
    fun execute(type: String): ScheduleDto
}