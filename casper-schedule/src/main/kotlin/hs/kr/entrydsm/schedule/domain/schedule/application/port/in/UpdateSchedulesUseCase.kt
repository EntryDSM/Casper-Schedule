package hs.kr.entrydsm.schedule.domain.schedule.application.port.`in`

import hs.kr.entrydsm.schedule.domain.schedule.adapter.`in`.schedule.dto.request.UpdateSchedulesRequest

interface UpdateSchedulesUseCase {
    /**
     * 일정을 일괄 업데이트합니다.
     *
     * @param request 업데이트할 일정 목록을 담은 요청 객체
     * @throws InvalidScheduleRequestException 유효하지 않은 요청인 경우
     * @throws InvalidScheduleSequenceException 일정 순서가 유효하지 않은 경우
     */
    fun execute(request: UpdateSchedulesRequest)
}