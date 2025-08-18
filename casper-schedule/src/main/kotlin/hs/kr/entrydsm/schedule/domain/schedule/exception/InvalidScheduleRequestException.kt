package hs.kr.entrydsm.schedule.domain.schedule.exception

import hs.kr.entrydsm.schedule.global.error.exception.CasperException
import hs.kr.entrydsm.schedule.global.error.exception.ErrorCode

/**
 * 잘못된 일정 요청 예외입니다.
 * 요청된 일정 데이터가 유효성 검사를 통과하지 못한 경우 발생합니다.
 */
object InvalidScheduleRequestException : CasperException(
    ErrorCode.INVALID_SCHEDULE
)
