package hs.kr.entrydsm.schedule.global.error.exception

/**
 * 애플리케이션에서 발생하는 모든 커스텀 예외의 기본 추상 클래스입니다.
 * 이 클래스를 상속받는 예외들은 각각의 도메인별로 정의되어 사용됩니다.
 *
 * @property errorCode 예외와 관련된 에러 코드 (상태 코드와 메시지 포함)
 */
abstract class CasperException(
    val errorCode: ErrorCode
) : RuntimeException()
