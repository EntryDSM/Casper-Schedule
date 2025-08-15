package hs.kr.entrydsm.schedule.global.error.exception

/**
 * 애플리케이션에서 사용되는 에러 코드를 정의한 enum 클래스입니다.
 * 각 에러 코드는 HTTP 상태 코드와 에러 메시지를 포함합니다.
 *
 * @property status HTTP 상태 코드
 * @property message 클라이언트에 표시될 에러 메시지
 */
enum class ErrorCode(
    val status: Int,
    val message: String
) {
    // Internal Server Error
    INTERNAL_SERVER_ERROR(500, "Internal Server Error"),

    // UnAuthorization
    INVALID_TOKEN(401, "Invalid Token"),
    EXPIRED_TOKEN(401, "Expired Token"),

    // Schedule
    DATE_SEQUENCE_NOT_VALID(400, "Schedule sequence is not valid"),
    INVALID_SCHEDULE(400, "Schedule list is Empty."),
    SCHEDULE_NOT_FOUND(404, "Schedule Not Found"),

    TYPE_MAPPING_ERROR(400, "Schedule Type Mapping Error")
}
