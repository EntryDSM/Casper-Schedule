package hs.kr.entrydsm.schedule.adapter.out.mapper.schedule

import hs.kr.entrydsm.schedule.adapter.out.entity.schedule.ScheduleJpaEntity
import hs.kr.entrydsm.schedule.model.schedule.Schedule
import org.mapstruct.Mapper

/**
 * Schedule 도메인 모델과 ScheduleJpaEntity 간의 변환을 담당하는 매퍼 인터페이스입니다.
 * MapStruct를 사용하여 구현체를 자동으로 생성합니다.
 */
@Mapper(componentModel = "spring")
interface ScheduleMapper {
    /**
     * ScheduleJpaEntity를 Schedule 도메인 모델로 변환합니다.
     *
     * @param entity 변환할 JPA 엔티티 객체
     * @return 변환된 Schedule 도메인 모델
     */
    fun toModel(entity: ScheduleJpaEntity): Schedule

    /**
     * Schedule 도메인 모델을 ScheduleJpaEntity로 변환합니다.
     *
     * @param model 변환할 도메인 모델
     * @return 변환된 JPA 엔티티 객체
     */
    fun toEntity(model: Schedule): ScheduleJpaEntity
}
