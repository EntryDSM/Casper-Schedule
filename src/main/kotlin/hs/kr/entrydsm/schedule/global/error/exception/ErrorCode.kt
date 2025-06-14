package hs.kr.entrydsm.schedule.global.error.exception

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
    SCHEDULE_NOT_FOUND(404, "Schedule Not Found")
}