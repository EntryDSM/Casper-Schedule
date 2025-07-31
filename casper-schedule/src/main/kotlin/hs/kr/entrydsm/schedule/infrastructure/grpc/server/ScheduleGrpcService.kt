package hs.kr.entrydsm.schedule.infrastructure.grpc.server

import hs.kr.entrydsm.casper.schedule.proto.ScheduleServiceGrpcKt
import hs.kr.entrydsm.casper.schedule.proto.ScheduleServiceProto
import hs.kr.entrydsm.schedule.domain.schedule.application.port.`in`.QueryScheduleByTypeUseCase
import hs.kr.entrydsm.schedule.infrastructure.grpc.dto.response.InternalScheduleResponse
import hs.kr.entrydsm.schedule.infrastructure.grpc.server.mapper.ScheduleGrpcMapper
import net.devh.boot.grpc.server.service.GrpcService

/**
 * gRPC 서버 구현체로, 스케줄 관련 gRPC 요청을 처리합니다.
 * ScheduleService gRPC 서비스 정의를 구현하며, 클라이언트로부터의 스케줄 조회 요청을 처리합니다.
 *
 * @property scheduleGrpcMapper 도메인 모델과 gRPC 메시지 간의 변환을 담당하는 매퍼
 * @property queryScheduleByTypeUseCase 특정 유형의 스케줄을 조회하기 위한 유스케이스
 */
@GrpcService
class ScheduleGrpcService(
    private val scheduleGrpcMapper: ScheduleGrpcMapper,
    private val queryScheduleByTypeUseCase: QueryScheduleByTypeUseCase
) : ScheduleServiceGrpcKt.ScheduleServiceCoroutineImplBase() {

    /**
     * 주어진 타입에 해당하는 스케줄을 조회합니다.
     *
     * @param request 조회할 스케줄의 타입이 포함된 요청 메시지
     * @return 조회된 스케줄 정보가 포함된 응답 메시지
     * @throws hs.kr.entrydsm.common.exception.BusinessException 해당 타입의 스케줄이 존재하지 않는 경우 발생
     */
    override suspend fun getScheduleByType(request: ScheduleServiceProto.TypeRequest): ScheduleServiceProto.GetScheduleResponse {
        val schedule = queryScheduleByTypeUseCase.execute(request.type.toString())
        val internalScheduleResponse = InternalScheduleResponse(
            type = schedule.type,
            date = schedule.date.toString()
        )
        return scheduleGrpcMapper.toGetScheduleByTypeResponse(internalScheduleResponse)
    }
}