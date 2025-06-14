package hs.kr.entrydsm.schedule.domain.schedule.exception

import hs.kr.entrydsm.schedule.global.error.exception.CasperException
import hs.kr.entrydsm.schedule.global.error.exception.ErrorCode

object InvalidScheduleSequenceException : CasperException(
    ErrorCode.DATE_SEQUENCE_NOT_VALID
)