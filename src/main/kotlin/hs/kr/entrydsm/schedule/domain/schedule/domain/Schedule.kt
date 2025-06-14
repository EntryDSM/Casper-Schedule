package hs.kr.entrydsm.schedule.domain.schedule.domain

import hs.kr.entrydsm.schedule.domain.schedule.domain.types.Type
import jakarta.persistence.*
import java.time.LocalDateTime

@Entity(name = "tbl_schedule")
class Schedule(
    @Id
    val id: Int = 0,

    @Enumerated(EnumType.STRING)
    @Column(length = 19, unique = true, nullable = false)
    val type: Type,

    @Column(nullable = false)
    var date: LocalDateTime
) {
    fun updateDate(date: LocalDateTime) {
        this.date = date
    }
}