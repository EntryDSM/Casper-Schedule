package hs.kr.entrydsm.schedule.global.exception

import hs.kr.entrydsm.schedule.global.error.exception.CasperException
import hs.kr.entrydsm.schedule.global.error.exception.ErrorCode

object InternalServerErrorException : CasperException(
    ErrorCode.INTERNAL_SERVER_ERROR
)