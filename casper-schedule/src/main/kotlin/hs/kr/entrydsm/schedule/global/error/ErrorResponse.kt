package hs.kr.entrydsm.schedule.global.error

/**
 * 에러 응답을 나타내는 데이터 클래스입니다.
 * API에서 발생한 오류에 대한 정보를 클라이언트에 전달할 때 사용됩니다.
 *
 * @property status HTTP 상태 코드 (예: 400, 401, 404, 500 등)
 * @property message 에러에 대한 상세 메시지 (사용자에게 표시될 수 있음)
 */
data class ErrorResponse(
    val status: Int,
    val message: String?
)
