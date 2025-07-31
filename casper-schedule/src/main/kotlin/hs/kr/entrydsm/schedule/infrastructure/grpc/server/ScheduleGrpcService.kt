package hs.kr.entrydsm.schedule.infrastructure.grpc.server

import com.casper.schedule.ScheduleProto
import com.casper.schedule.ScheduleServiceGrpcKt
import com.google.protobuf.Empty
import hs.kr.entrydsm.schedule.domain.schedule.application.port.`in`.QuerySchedulesUseCase
import hs.kr.entrydsm.schedule.infrastructure.grpc.server.mapper.ScheduleGrpcMapper
import net.devh.boot.grpc.server.service.GrpcService
import org.springframework.transaction.annotation.Transactional

/**
 * gRPC 서버 구현체로, 스케줄 관련 gRPC 요청을 처리합니다.
 * 이 서비스는 ScheduleService gRPC 서비스 정의를 구현하며,
 * 클라이언트로부터의 스케줄 조회 요청을 처리하는 메서드를 제공합니다.
 *
 * @property scheduleGrpcMapper gRPC 메시지와 도메인 모델 간의 변환을 담당하는 매퍼
 * @property querySchedulesUseCase 스케줄 조회를 위한 유스케이스
 */
@GrpcService
class ScheduleGrpcService(
    private val scheduleGrpcMapper: ScheduleGrpcMapper,
    private val querySchedulesUseCase: QuerySchedulesUseCase
) : ScheduleServiceGrpcKt.ScheduleServiceCoroutineImplBase() {
    /**
     * 등록된 모든 스케줄을 조회하여 반환합니다.
     * 이 메서드는 트랜잭션 내에서 읽기 전용으로 실행됩니다.
     *
     * @param request 빈 요청 메시지 (Empty)
     * @return ScheduleProto.SchedulesResponse 조회된 모든 스케줄이 포함된 응답
     * @throws Exception 스케줄 조회 중 오류가 발생한 경우
     */
    @Transactional(readOnly = true)
    override suspend fun getSchedules(request: Empty): ScheduleProto.SchedulesResponse {
        val schedules = querySchedulesUseCase.execute()
        return scheduleGrpcMapper.toSchedulesProto(schedules)
    }
}