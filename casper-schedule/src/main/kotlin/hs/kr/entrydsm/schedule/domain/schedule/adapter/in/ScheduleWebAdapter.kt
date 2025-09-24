package hs.kr.entrydsm.schedule.domain.schedule.adapter.`in`

import hs.kr.entrydsm.schedule.domain.schedule.adapter.`in`.dto.ScheduleDto
import hs.kr.entrydsm.schedule.domain.schedule.adapter.`in`.dto.request.UpdateSchedulesRequest
import hs.kr.entrydsm.schedule.domain.schedule.adapter.`in`.dto.response.SchedulesResponse
import hs.kr.entrydsm.schedule.domain.schedule.application.port.`in`.QueryScheduleByTypeUseCase
import hs.kr.entrydsm.schedule.domain.schedule.application.port.`in`.QuerySchedulesUseCase
import hs.kr.entrydsm.schedule.domain.schedule.application.port.`in`.UpdateSchedulesUseCase
import hs.kr.entrydsm.schedule.global.document.schedule.ScheduleApiDocument
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

/**
 * 스케줄 관련 HTTP 요청을 처리하는 REST 컨트롤러입니다.
 * 클라이언트의 요청을 적절한 유스케이스에 위임하고 결과를 반환합니다.
 *
 * @property queryScheduleByTypeUseCase 타입별 스케줄 조회 유스케이스
 * @property querySchedulesUseCase 모든 스케줄 조회 유스케이스
 * @property updateSchedulesUseCase 스케줄 업데이트 유스케이스
 */
@RestController
@RequestMapping("/schedule")
class ScheduleWebAdapter(
    private val queryScheduleByTypeUseCase: QueryScheduleByTypeUseCase,
    private val querySchedulesUseCase: QuerySchedulesUseCase,
    private val updateSchedulesUseCase: UpdateSchedulesUseCase
) : ScheduleApiDocument {
    @GetMapping(params = ["type"])
    override fun queryScheduleByType(
        @RequestParam type: String
    ): ScheduleDto = queryScheduleByTypeUseCase.execute(type)

    /**
     * 모든 일정을 조회합니다.
     *
     * @return 모든 일정 정보를 담은 응답 객체
     */
    @GetMapping("/all")
    override fun querySchedules(): SchedulesResponse = querySchedulesUseCase.execute()

    /**
     * 일정을 일괄 업데이트합니다.
     *
     * @param request 업데이트할 일정 목록을 담은 요청 객체
     * @return 업데이트된 일정 정보를 담은 응답 객체
     */
    @PatchMapping
    override fun updateSchedules(
        @RequestBody @Valid
        request: UpdateSchedulesRequest
    ) = updateSchedulesUseCase.execute(request)
}