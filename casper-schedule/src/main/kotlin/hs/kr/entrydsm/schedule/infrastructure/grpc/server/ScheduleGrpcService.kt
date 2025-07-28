package hs.kr.entrydsm.schedule.infrastructure.grpc.server

import com.casper.schedule.ScheduleProto
import com.casper.schedule.ScheduleServiceGrpcKt
import com.google.protobuf.Empty
import hs.kr.entrydsm.schedule.domain.schedule.application.port.`in`.QuerySchedulesUseCase
import hs.kr.entrydsm.schedule.infrastructure.grpc.server.mapper.ScheduleGrpcMapper
import net.devh.boot.grpc.server.service.GrpcService
import org.springframework.transaction.annotation.Transactional

/**
 * gRPC 서비스를 제공하는 ScheduleGrpcService 클래스입니다.
 * 스케줄 관련 gRPC 요청을 처리합니다.
 *
 * @property scheduleGrpcMapper ScheduleGrpcMapper 인스턴스
 * @property scheduleService ScheduleService 인스턴스
 */
@GrpcService
class ScheduleGrpcService(
    private val scheduleGrpcMapper: ScheduleGrpcMapper,
    private val querySchedulesUseCase: QuerySchedulesUseCase
) : ScheduleServiceGrpcKt.ScheduleServiceCoroutineImplBase() {
    /**
     * 모든 스케줄을 조회하는 메서드입니다.
     *
     * @param request 빈 요청 메시지 (Empty)
     * @return ScheduleProto.SchedulesResponse 조회된 스케줄 목록이 포함된 응답
     */
    @Transactional(readOnly = true)
    override suspend fun getSchedules(request: Empty): ScheduleProto.SchedulesResponse {
        val schedules = querySchedulesUseCase.execute()
        return scheduleGrpcMapper.toSchedulesProto(schedules)
    }
}