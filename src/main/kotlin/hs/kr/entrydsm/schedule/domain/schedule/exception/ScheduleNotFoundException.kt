package hs.kr.entrydsm.schedule.domain.schedule.exception

import hs.kr.entrydsm.schedule.global.error.exception.CasperException
import hs.kr.entrydsm.schedule.global.error.exception.ErrorCode

object ScheduleNotFoundException : CasperException(
    ErrorCode.SCHEDULE_NOT_FOUND
)