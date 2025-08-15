package hs.kr.entrydsm.schedule.global.exception

import hs.kr.entrydsm.schedule.global.error.exception.CasperException
import hs.kr.entrydsm.schedule.global.error.exception.ErrorCode

/**
 * 만료된 토큰 예외입니다.
 * JWT 토큰의 유효기간이 지난 경우 발생합니다.
 */
object ExpiredTokenException : CasperException(
    ErrorCode.EXPIRED_TOKEN
)
