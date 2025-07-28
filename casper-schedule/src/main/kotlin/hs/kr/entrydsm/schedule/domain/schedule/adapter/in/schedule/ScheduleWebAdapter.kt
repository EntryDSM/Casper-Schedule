package hs.kr.entrydsm.schedule.domain.schedule.adapter.`in`.schedule

import hs.kr.entrydsm.schedule.domain.schedule.adapter.`in`.schedule.dto.ScheduleDto
import hs.kr.entrydsm.schedule.domain.schedule.adapter.`in`.schedule.dto.request.UpdateSchedulesRequest
import hs.kr.entrydsm.schedule.domain.schedule.adapter.`in`.schedule.dto.response.SchedulesResponse
import hs.kr.entrydsm.schedule.domain.schedule.application.port.`in`.QueryScheduleByTypeUseCase
import hs.kr.entrydsm.schedule.domain.schedule.application.port.`in`.QuerySchedulesUseCase
import hs.kr.entrydsm.schedule.domain.schedule.application.port.`in`.UpdateSchedulesUseCase
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/schedule")
class ScheduleWebAdapter(
    private val queryScheduleByTypeUseCase: QueryScheduleByTypeUseCase,
    private val querySchedulesUseCase: QuerySchedulesUseCase,
    private val updateSchedulesUseCase: UpdateSchedulesUseCase
) {
    @GetMapping(params = ["type"])
    fun queryScheduleByType(
        @RequestParam type: String
    ): ScheduleDto = queryScheduleByTypeUseCase.execute(type)

    /**
     * 모든 일정을 조회합니다.
     *
     * @return 모든 일정 정보를 담은 응답 객체
     */
    @GetMapping
    fun querySchedules(): SchedulesResponse = querySchedulesUseCase.execute()

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
    ) = updateSchedulesUseCase.execute(request)
}
