package hs.kr.entrydsm.schedule.infrastructure.grpc.server.error

import hs.kr.entrydsm.schedule.infrastructure.grpc.server.exception.TypeMappingException
import io.grpc.Status
import io.grpc.StatusException
import net.devh.boot.grpc.server.advice.GrpcAdvice
import net.devh.boot.grpc.server.advice.GrpcExceptionHandler

/**
 * gRPC 예외 처리를 담당하는 클래스입니다.
 * gRPC 서비스에서 발생하는 예외를 적절한 gRPC 상태 코드로 변환하여 클라이언트에 전달합니다.
 */
@GrpcAdvice
class GrpcExceptionHandler {
    /**
     * TypeMappingException을 처리하여 적절한 gRPC 상태 코드로 변환합니다.
     *
     * @param e 처리할 TypeMappingException 예외 객체
     * @return INVALID_ARGUMENT 상태 코드와 함께 StatusException 반환
     */
    @GrpcExceptionHandler(TypeMappingException::class)
    fun handleTypeMappingException(e: TypeMappingException): StatusException {
        return Status.INVALID_ARGUMENT
            .withDescription(e.message)
            .asException()
    }
}