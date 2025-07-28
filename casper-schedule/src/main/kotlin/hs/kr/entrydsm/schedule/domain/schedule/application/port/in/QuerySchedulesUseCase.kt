package hs.kr.entrydsm.schedule.domain.schedule.application.port.`in`

import hs.kr.entrydsm.schedule.domain.schedule.adapter.`in`.dto.response.SchedulesResponse

interface QuerySchedulesUseCase {
    /**
     * 모든 일정을 조회합니다.
     *
     * @return 모든 일정 정보를 담은 응답 객체
     */
    fun execute(): SchedulesResponse
}