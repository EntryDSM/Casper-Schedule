package hs.kr.entrydsm.schedule.domain.schedule.application.port.`in`

import hs.kr.entrydsm.schedule.domain.schedule.adapter.`in`.dto.response.SchedulesResponse

/**
 * 모든 일정을 조회하기 위한 유스케이스 인터페이스입니다.
 * 이 인터페이스는 도메인 서비스에서 모든 일정을 조회할 때 사용됩니다.
 */
interface QuerySchedulesUseCase {
    /**
     * 모든 일정을 조회합니다.
     *
     * @return 모든 일정 정보를 담은 응답 객체
     */
    fun execute(): SchedulesResponse
}