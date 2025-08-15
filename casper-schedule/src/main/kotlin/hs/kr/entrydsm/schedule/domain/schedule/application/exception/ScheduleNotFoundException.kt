package hs.kr.entrydsm.schedule.domain.schedule.application.exception

import hs.kr.entrydsm.schedule.global.error.exception.CasperException
import hs.kr.entrydsm.schedule.global.error.exception.ErrorCode

/**
 * 일정을 찾을 수 없는 경우 발생하는 예외입니다.
 * 요청한 일정이 존재하지 않을 때 사용됩니다.
 */
object ScheduleNotFoundException : CasperException(
    ErrorCode.SCHEDULE_NOT_FOUND
)
