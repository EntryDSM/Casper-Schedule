package hs.kr.entrydsm.schedule.domain.schedule.exception

import hs.kr.entrydsm.schedule.global.error.exception.CasperException
import hs.kr.entrydsm.schedule.global.error.exception.ErrorCode

object InvalidScheduleRequestException : CasperException(
    ErrorCode.INVALID_SCHEDULE
)