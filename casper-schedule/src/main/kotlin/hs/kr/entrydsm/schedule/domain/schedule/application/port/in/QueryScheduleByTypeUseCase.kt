package hs.kr.entrydsm.schedule.domain.schedule.application.port.`in`

import hs.kr.entrydsm.schedule.domain.schedule.adapter.`in`.dto.ScheduleDto

/**
 * 특정 타입의 일정을 조회하기 위한 유스케이스 인터페이스입니다.
 * 이 인터페이스는 도메인 서비스에서 특정 타입의 일정을 조회할 때 사용됩니다.
 */
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