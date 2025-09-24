package hs.kr.entrydsm.schedule.domain.schedule.adapter.out.entity

import hs.kr.entrydsm.schedule.domain.schedule.model.type.Type
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import java.time.LocalDateTime

/**
 * JPA 엔티티 클래스로, 데이터베이스의 스케줄 테이블과 매핑됩니다.
 *
 * @property id 스케줄의 고유 식별자
 * @property type 스케줄 유형 (START_DATE, FIRST_ANNOUNCEMENT, INTERVIEW, SECOND_ANNOUNCEMENT, END_DATE)
 * @property date 스케줄 날짜 및 시간
 */
@Entity(name = "tbl_schedule")
class ScheduleJpaEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int? = null,
    @Enumerated(EnumType.STRING)
    @Column(length = 19, unique = true, nullable = false)
    val type: Type,
    @Column(nullable = false)
    var date: LocalDateTime
)