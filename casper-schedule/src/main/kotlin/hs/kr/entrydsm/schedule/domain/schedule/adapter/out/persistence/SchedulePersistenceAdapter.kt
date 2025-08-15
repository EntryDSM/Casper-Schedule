package hs.kr.entrydsm.schedule.domain.schedule.adapter.out.persistence

import hs.kr.entrydsm.schedule.domain.schedule.adapter.out.mapper.ScheduleMapper
import hs.kr.entrydsm.schedule.domain.schedule.adapter.out.persistence.repository.ScheduleRepository
import hs.kr.entrydsm.schedule.domain.schedule.application.port.out.FindSchedulePort
import hs.kr.entrydsm.schedule.domain.schedule.application.port.out.SaveSchedulePort
import hs.kr.entrydsm.schedule.domain.schedule.model.Schedule
import hs.kr.entrydsm.schedule.domain.schedule.model.type.Type
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
    /**
     * 주어진 타입에 해당하는 스케줄을 조회합니다.
     *
     * @param type 조회할 스케줄의 타입
     * @return 해당 타입의 스케줄이 존재하면 Schedule 객체를, 없으면 null을 반환
     */
    override fun findByType(type: Type): Schedule? {
        return scheduleRepository.findByType(type)?.let { scheduleMapper.toModel(it) }
    }

    /**
     * 모든 스케줄을 조회합니다.
     *
     * @return 저장된 모든 스케줄의 리스트를 반환합니다. 스케줄이 없을 경우 빈 리스트를 반환합니다.
     */
    override fun findAllBy(): List<Schedule> {
        return scheduleRepository.findAllBy().map { scheduleMapper.toModel(it) }
    }

    /**
     * 스케줄을 저장하거나 업데이트합니다.
     *
     * @param schedule 저장할 스케줄 도메인 모델
     * @return 저장된 스케줄 도메인 모델
     */
    override fun saveSchedule(schedule: Schedule): Schedule {
        return scheduleMapper.toModel(scheduleRepository.save(scheduleMapper.toEntity(schedule)))
    }
}