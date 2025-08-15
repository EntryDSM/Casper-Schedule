package hs.kr.entrydsm.schedule.infrastructure.grpc.server.mapper

import hs.kr.entrydsm.casper.schedule.proto.ScheduleServiceProto
import hs.kr.entrydsm.schedule.domain.schedule.model.type.Type
import hs.kr.entrydsm.schedule.infrastructure.grpc.dto.response.InternalScheduleResponse
import org.springframework.stereotype.Component

/**
 * 도메인 모델과 gRPC 메시지 간의 변환을 담당하는 매퍼 클래스입니다.
 * 이 클래스는 내부 도메인 모델과 gRPC 프로토콜 버퍼 메시지 간의 변환 로직을 캡슐화합니다.
 */
@Component
class ScheduleGrpcMapper {
    companion object {
        /**
         * 도메인 타입과 gRPC 프로토 타입 간의 매핑을 정의한 맵입니다.
         * 이 맵은 도메인 모델의 Type과 프로토콜 버퍼의 Type 간의 변환을 위해 사용됩니다.
         */
        private val DOMAIN_TO_PROTO_MAP = mapOf(
            Type.START_DATE to ScheduleServiceProto.Type.START_DATE,
            Type.FIRST_ANNOUNCEMENT to ScheduleServiceProto.Type.FIRST_ANNOUNCEMENT,
            Type.INTERVIEW to ScheduleServiceProto.Type.INTERVIEW,
            Type.SECOND_ANNOUNCEMENT to ScheduleServiceProto.Type.SECOND_ANNOUNCEMENT,
            Type.END_DATE to ScheduleServiceProto.Type.END_DATE
        )
    }

    /**
     * 내부 스케줄 응답을 gRPC 응답 메시지로 변환합니다.
     *
     * @param response 변환할 내부 스케줄 응답 객체
     * @return gRPC 프로토콜 버퍼 형식의 응답 메시지
     * @throws NoSuchElementException 매핑된 프로토 타입을 찾을 수 없는 경우 발생
     */
    fun toGetScheduleByTypeResponse(response: InternalScheduleResponse): ScheduleServiceProto.GetScheduleResponse {
        return ScheduleServiceProto.GetScheduleResponse.newBuilder()
            .setType(toProtoType(response.type))
            .setDate(response.date)
            .build()
    }

    /**
     * 도메인 타입을 gRPC 프로토 타입으로 변환합니다.
     *
     * @param type 변환할 도메인 타입
     * @return 변환된 gRPC 프로토 타입
     * @throws NoSuchElementException 매핑된 프로토 타입을 찾을 수 없는 경우 발생
     */
    private fun toProtoType(type: Type): ScheduleServiceProto.Type = DOMAIN_TO_PROTO_MAP[type]!!
}