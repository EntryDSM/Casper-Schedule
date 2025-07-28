package hs.kr.entrydsm.schedule.adapter.out.persistence.schedule.repository

import hs.kr.entrydsm.schedule.adapter.out.entity.schedule.ScheduleJpaEntity
import hs.kr.entrydsm.schedule.model.schedule.type.Type
import org.springframework.data.jpa.repository.JpaRepository

/**
 * 일정 정보에 접근하기 위한 JPA 리포지토리 인터페이스입니다.
 * ScheduleJpaEntity에 대한 데이터베이스 작업을 추상화합니다.
 */
interface ScheduleRepository : JpaRepository<ScheduleJpaEntity, Int> {
    /**
     * 모든 일정 엔티티를 조회합니다.
     *
     * @return 일정 엔티티 목록 (없을 경우 빈 리스트)
     */
    fun findAllBy(): List<ScheduleJpaEntity>

    /**
     * 지정된 타입의 일정 엔티티를 조회합니다.
     *
     * @param type 조회할 일정 타입
     * @return 일치하는 일정 엔티티 (없을 경우 null)
     */
    fun findByType(type: Type): ScheduleJpaEntity?
}
