package hs.kr.entrydsm.schedule.global.exception

import hs.kr.entrydsm.schedule.global.error.exception.CasperException
import hs.kr.entrydsm.schedule.global.error.exception.ErrorCode

/**
 * 서버 내부 오류 예외입니다.
 * 예상치 못한 오류가 발생한 경우 사용됩니다.
 */
object InternalServerErrorException : CasperException(
    ErrorCode.INTERNAL_SERVER_ERROR
)
