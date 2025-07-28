package hs.kr.entrydsm.schedule.domain.schedule.adapter.`in`.schedule

import hs.kr.entrydsm.schedule.domain.schedule.adapter.`in`.schedule.dto.ScheduleDto
import hs.kr.entrydsm.schedule.domain.schedule.adapter.`in`.schedule.dto.request.UpdateSchedulesRequest
import hs.kr.entrydsm.schedule.domain.schedule.adapter.`in`.schedule.dto.response.SchedulesResponse
import hs.kr.entrydsm.schedule.domain.schedule.application.service.ScheduleService
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

/**
 * 스케줄 관련 HTTP 요청을 처리하는 웹 어댑터 클래스입니다.
 * REST API 엔드포인트를 제공하며, 클라이언트의 요청을 적절한 서비스 메서드로 라우팅합니다.
 *
 * @property scheduleService 스케줄 관련 비즈니스 로직을 처리하는 서비스
 */
@RestController
@RequestMapping("/schedule")
class ScheduleWebAdapter(
    private val scheduleService: ScheduleService
) {
    @GetMapping(params = ["type"])
    fun queryScheduleByType(
        @RequestParam type: String
    ): ScheduleDto = scheduleService.queryScheduleByType(type)

    /**
     * 모든 일정을 조회합니다.
     *
     * @return 모든 일정 정보를 담은 응답 객체
     */
    @GetMapping
    fun querySchedules(): SchedulesResponse = scheduleService.querySchedules()

    /**
     * 일정을 일괄 업데이트합니다.
     *
     * @param request 업데이트할 일정 목록을 담은 요청 객체
     * @return 업데이트된 일정 정보를 담은 응답 객체
     */
    @PatchMapping
    fun updateSchedules(
        @RequestBody @Valid
        request: UpdateSchedulesRequest
    ) = scheduleService.updateSchedules(request)
}
