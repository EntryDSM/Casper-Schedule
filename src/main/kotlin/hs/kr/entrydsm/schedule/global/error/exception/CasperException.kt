package hs.kr.entrydsm.schedule.global.error.exception

abstract class CasperException(
    val errorCode: ErrorCode
) : RuntimeException()
