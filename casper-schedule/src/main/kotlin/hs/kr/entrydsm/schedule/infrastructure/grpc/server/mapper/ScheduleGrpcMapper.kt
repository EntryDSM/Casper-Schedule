package hs.kr.entrydsm.schedule.infrastructure.grpc.server.mapper

import com.casper.schedule.ScheduleProto
import hs.kr.entrydsm.schedule.domain.schedule.adapter.`in`.dto.ScheduleDto
import hs.kr.entrydsm.schedule.domain.schedule.adapter.`in`.dto.response.SchedulesResponse
import hs.kr.entrydsm.schedule.infrastructure.grpc.server.exception.TypeMappingException
import hs.kr.entrydsm.schedule.domain.schedule.model.type.Type
import org.springframework.stereotype.Component
import java.time.format.DateTimeFormatter

/**
 * 도메인 모델과 gRPC 프로토콜 버퍼 모델 간의 변환을 담당하는 매퍼 클래스입니다.
 *
 * @property formatter 날짜 형식을 지정하는 DateTimeFormatter 인스턴스 (ISO_DATE 형식 사용)
 * @property DOMAIN_TO_PROTO_MAP 도메인 타입을 프로토콜 버퍼 타입으로 매핑하는 맵
 */
@Component
class ScheduleGrpcMapper {
    companion object {
        private val formatter = DateTimeFormatter.ISO_DATE

        private val DOMAIN_TO_PROTO_MAP =
            mapOf(
                Type.START_DATE to ScheduleProto.Type.START_DATE,
                Type.FIRST_ANNOUNCEMENT to ScheduleProto.Type.FIRST_ANNOUNCEMENT,
                Type.INTERVIEW to ScheduleProto.Type.INTERVIEW,
                Type.SECOND_ANNOUNCEMENT to ScheduleProto.Type.SECOND_ANNOUNCEMENT,
                Type.END_DATE to ScheduleProto.Type.END_DATE
            )
    }

    /**
     * SchedulesResponse 도메인 객체를 gRPC 프로토콜 버퍼 객체로 변환합니다.
     *
     * @param schedules 변환할 SchedulesResponse 도메인 객체
     * @return 변환된 ScheduleProto.SchedulesResponse 객체
     */
    fun toSchedulesProto(schedules: SchedulesResponse): ScheduleProto.SchedulesResponse {
        val protoSchedules = schedules.schedules.map { toProto(it!!) }
        return ScheduleProto.SchedulesResponse.newBuilder()
            .addAllSchedules(protoSchedules)
            .setCurrentStatus(schedules.currentStatus)
            .build()
    }

    /**
     * ScheduleDto 객체를 gRPC 프로토콜 버퍼 객체로 변환합니다.
     *
     * @param scheduleDto 변환할 ScheduleDto 객체
     * @return 변환된 ScheduleProto.ScheduleResponse 객체
     */
    private fun toProto(scheduleDto: ScheduleDto): ScheduleProto.ScheduleResponse {
        return ScheduleProto.ScheduleResponse.newBuilder()
            .setType(toProtoType(scheduleDto.type))
            .setDate(scheduleDto.date.format(formatter))
            .build()
    }

    /**
     * 도메인 타입을 gRPC 프로토콜 버퍼 타입으로 변환합니다.
     *
     * @param type 변환할 도메인 타입
     * @return 변환된 프로토콜 버퍼 타입
     * @throws TypeMappingException 매핑할 수 없는 타입인 경우 예외 발생
     */
    private fun toProtoType(type: Type): ScheduleProto.Type =
        DOMAIN_TO_PROTO_MAP[type]
            ?: throw TypeMappingException
}