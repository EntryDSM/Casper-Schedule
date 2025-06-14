package hs.kr.entrydsm.schedule.domain.schedule.domain.repository

import hs.kr.entrydsm.schedule.domain.schedule.domain.Schedule
import hs.kr.entrydsm.schedule.domain.schedule.domain.types.Type
import org.springframework.data.jpa.repository.JpaRepository

interface ScheduleRepository : JpaRepository<Schedule, Int> {
    fun findAllBy(): List<Schedule>

    fun findByType(type: Type): Schedule?
}