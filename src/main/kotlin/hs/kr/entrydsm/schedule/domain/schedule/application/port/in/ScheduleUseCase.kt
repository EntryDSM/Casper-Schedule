package hs.kr.entrydsm.schedule.domain.schedule.application.port.`in`

import hs.kr.entrydsm.schedule.domain.schedule.adapter.`in`.schedule.dto.ScheduleDto
import hs.kr.entrydsm.schedule.domain.schedule.adapter.`in`.schedule.dto.request.UpdateSchedulesRequest
import hs.kr.entrydsm.schedule.domain.schedule.adapter.`in`.schedule.dto.response.SchedulesResponse

/**
 * 일정 관련 유스케이스 인터페이스입니다.
 * 웹 어댑터에서 호출되는 메서드들을 정의합니다.
 */
interface ScheduleUseCase {
    /**
     * 지정된 타입의 일정을 조회합니다.
     *
     * @param type 조회할 일정의 타입 (문자열)
     * @return 조회된 일정 DTO
     * @throws IllegalArgumentException 유효하지 않은 타입인 경우
     */
    fun queryScheduleByType(type: String): ScheduleDto

    /**
     * 모든 일정을 조회합니다.
     *
     * @return 모든 일정 정보를 담은 응답 객체
     */
    fun querySchedules(): SchedulesResponse

    /**
     * 일정을 일괄 업데이트합니다.
     *
     * @param request 업데이트할 일정 목록을 담은 요청 객체
     * @throws InvalidScheduleRequestException 유효하지 않은 요청인 경우
     * @throws InvalidScheduleSequenceException 일정 순서가 유효하지 않은 경우
     */
    fun updateSchedules(request: UpdateSchedulesRequest)
}
