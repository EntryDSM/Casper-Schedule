package hs.kr.entrydsm.schedule.global.document.schedule

import hs.kr.entrydsm.schedule.domain.schedule.adapter.`in`.dto.ScheduleDto
import hs.kr.entrydsm.schedule.domain.schedule.adapter.`in`.dto.request.UpdateSchedulesRequest
import hs.kr.entrydsm.schedule.domain.schedule.adapter.`in`.dto.response.SchedulesResponse
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam

@Tag(name = "Schedule", description = "전형 일정 API")
interface ScheduleApiDocument {

    @Operation(
        summary = "일정 종류별 전형 일정 조회",
        description = "일정 종류별 전형 일정을 조회합니다."
    )
    @ApiResponses(
        ApiResponse(
            responseCode = "200",
            description = "일정 종류별 전형 일정 조회 성공",
            content = arrayOf(Content())
        ),
        ApiResponse(
            responseCode = "404",
            description = "일정 종류에 알맞는 일정을 찾을 수 없습니다. - Schedule Not Found",
            content = arrayOf(Content())
        )
    )
    fun queryScheduleByType(
        @RequestParam type: String
    ): ScheduleDto

    @Operation(
        summary = "모든 전형 일정 조회",
        description = "모든 전형 일정을 조회합니다."
    )
    @ApiResponses(
        ApiResponse(
            responseCode = "200",
            description = "모든 전형 일정 조회 성공",
            content = arrayOf(Content())
        )
    )
    fun querySchedules(): SchedulesResponse

    @Operation(
        summary = "일전형 일정 수정",
        description = "전형 일정을 수정합니다."
    )
    @ApiResponses(
        ApiResponse(
            responseCode = "200",
            description = "일전형 일정 수정 성공",
            content = arrayOf(Content())
        ),
        ApiResponse(
            responseCode = "404",
            description = "일정 종류에 알맞는 일정을 찾을 수 없습니다. - Schedule Not Found",
            content = arrayOf(Content())
        )
    )
    fun updateSchedules(
        @RequestBody @Valid
        request: UpdateSchedulesRequest
    )
}