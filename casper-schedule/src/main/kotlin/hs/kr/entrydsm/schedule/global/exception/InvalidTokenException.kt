package hs.kr.entrydsm.schedule.global.exception

import hs.kr.entrydsm.schedule.global.error.exception.CasperException
import hs.kr.entrydsm.schedule.global.error.exception.ErrorCode

/**
 * 잘못된 토큰 예외입니다.
 * JWT 토큰이 유효하지 않거나 형식이 맞지 않는 경우 발생합니다.
 */
object InvalidTokenException : CasperException(
    ErrorCode.INVALID_TOKEN
)
