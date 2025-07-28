package hs.kr.entrydsm.schedule.application.schedule.exception

import hs.kr.entrydsm.schedule.global.error.exception.CasperException
import hs.kr.entrydsm.schedule.global.error.exception.ErrorCode

/**
 * 잘못된 일정 순서 예외입니다.
 * 일정의 시작일이 종료일보다 늦거나, 이전 일정과의 순서가 맞지 않는 경우 발생합니다.
 */
object InvalidScheduleSequenceException : CasperException(
    ErrorCode.DATE_SEQUENCE_NOT_VALID
)
