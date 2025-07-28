package hs.kr.entrydsm.schedule.infrastructure.grpc.server.exception

import hs.kr.entrydsm.schedule.global.error.exception.CasperException
import hs.kr.entrydsm.schedule.global.error.exception.ErrorCode

/**
 * 타입 매핑 실패 예외입니다.
 * gRPC 요청/응답 객체와 도메인 객체 간 변환 중 오류가 발생한 경우 사용됩니다.
 */
object TypeMappingException : CasperException(
    ErrorCode.TYPE_MAPPING_ERROR
)
