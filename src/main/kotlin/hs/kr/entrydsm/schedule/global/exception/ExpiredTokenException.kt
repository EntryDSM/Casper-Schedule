package hs.kr.entrydsm.schedule.global.exception

import hs.kr.entrydsm.schedule.global.error.exception.CasperException
import hs.kr.entrydsm.schedule.global.error.exception.ErrorCode

object ExpiredTokenException : CasperException(
    ErrorCode.EXPIRED_TOKEN
)