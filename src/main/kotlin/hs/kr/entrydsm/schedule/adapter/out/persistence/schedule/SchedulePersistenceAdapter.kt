package hs.kr.entrydsm.schedule.adapter.out.persistence.schedule

import hs.kr.entrydsm.schedule.adapter.out.mapper.schedule.ScheduleMapper
import hs.kr.entrydsm.schedule.adapter.out.persistence.schedule.repository.ScheduleRepository
import hs.kr.entrydsm.schedule.application.schedule.port.out.FindSchedulePort
import hs.kr.entrydsm.schedule.application.schedule.port.out.SaveSchedulePort
import hs.kr.entrydsm.schedule.model.schedule.Schedule
import hs.kr.entrydsm.schedule.model.schedule.type.Type
import org.springframework.stereotype.Component

/**
 * 스케줄 관련 데이터베이스 접근을 담당하는 어댑터 클래스입니다.
 * 도메인 모델과 JPA 엔티티 간의 변환을 처리하며, 스케줄 조회 및 저장 기능을 제공합니다.
 *
 * @property scheduleRepository 스케줄 데이터 접근을 위한 JPA 레포지토리
 * @property scheduleMapper 도메인 모델과 JPA 엔티티 간의 매핑을 담당하는 매퍼
 */
@Component
class SchedulePersistenceAdapter(
    private val scheduleRepository: ScheduleRepository,
    private val scheduleMapper: ScheduleMapper
) : FindSchedulePort, SaveSchedulePort {
    override fun findByType(type: Type): Schedule? {
        return scheduleRepository.findByType(type)?.let { scheduleMapper.toModel(it) }
    }

    override fun findAllBy(): List<Schedule> {
        return scheduleRepository.findAllBy().map { scheduleMapper.toModel(it) }
    }

    override fun saveSchedule(schedule: Schedule): Schedule {
        return scheduleMapper.toModel(scheduleRepository.save(scheduleMapper.toEntity(schedule)))
    }
}
