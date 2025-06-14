package hs.kr.entrydsm.schedule.domain.schedule.presentation.dto

import com.fasterxml.jackson.annotation.JsonFormat
import hs.kr.entrydsm.schedule.domain.schedule.domain.types.Type
import jakarta.validation.constraints.NotNull
import java.time.LocalDateTime

data class ScheduleDto(
    @NotNull
    val type: Type,

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "Asia/Seoul")
    @NotNull
    val date: LocalDateTime
)