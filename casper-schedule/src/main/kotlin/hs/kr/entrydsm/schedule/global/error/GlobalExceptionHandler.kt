package hs.kr.entrydsm.schedule.global.error

import hs.kr.entrydsm.schedule.global.error.exception.CasperException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

/**
 * 전역 예외 처리를 담당하는 핸들러 클래스입니다.
 * 컨트롤러에서 발생하는 예외를 캐치하여 일관된 형식의 에러 응답을 반환합니다.
 *
 * @see CasperException 커스텀 예외 클래스
 * @see ErrorResponse 에러 응답 DTO
 */
@RestControllerAdvice
class GlobalExceptionHandler {
    /**
     * CasperException을 처리하여 적절한 HTTP 응답을 반환합니다.
     *
     * @param e 처리할 CasperException 예외 객체
     * @return ErrorResponse를 포함하는 ResponseEntity
     */
    @ExceptionHandler(CasperException::class)
    fun handlingEquusException(e: CasperException): ResponseEntity<ErrorResponse> {
        val code = e.errorCode
        return ResponseEntity(
            ErrorResponse(code.status, code.message),
            org.springframework.http.HttpStatus.valueOf(code.status)
        )
    }

    /**
     * 유효성 검사 실패 시 발생하는 MethodArgumentNotValidException을 처리합니다.
     *
     * @param e 처리할 MethodArgumentNotValidException 예외 객체
     * @return 400 Bad Request 상태 코드와 함께 에러 메시지를 포함하는 ResponseEntity
     */
    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun validatorExceptionHandler(e: MethodArgumentNotValidException): ResponseEntity<ErrorResponse> {
        return ResponseEntity(
            ErrorResponse(
                400,
                e.bindingResult.allErrors[0].defaultMessage
            ),
            HttpStatus.BAD_REQUEST
        )
    }
}
