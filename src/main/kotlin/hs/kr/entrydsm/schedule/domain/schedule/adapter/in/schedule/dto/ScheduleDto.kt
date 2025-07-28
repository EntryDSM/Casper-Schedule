package hs.kr.entrydsm.schedule.adapter.`in`.schedule.dto

import com.fasterxml.jackson.annotation.JsonFormat
import hs.kr.entrydsm.schedule.model.schedule.type.Type
import jakarta.validation.constraints.NotNull
import java.time.LocalDateTime

/**
 * 스케줄 정보를 담는 데이터 전송 객체(DTO)입니다.
 *
 * @property type 스케줄 유형 (START_DATE, FIRST_ANNOUNCEMENT, INTERVIEW, SECOND_ANNOUNCEMENT, END_DATE)
 * @property date 스케줄 날짜 및 시간 (Asia/Seoul 시간대, yyyy-MM-dd'T'HH:mm:ss 형식)
 */
data class ScheduleDto(
    @field:NotNull
    val type: Type,
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "Asia/Seoul")
    @field:NotNull
    val date: LocalDateTime
)
