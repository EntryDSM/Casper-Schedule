package hs.kr.entrydsm.schedule.global.error

import com.fasterxml.jackson.databind.ObjectMapper
import hs.kr.entrydsm.schedule.global.error.exception.CasperException
import hs.kr.entrydsm.schedule.global.error.exception.ErrorCode
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.MediaType
import org.springframework.web.filter.OncePerRequestFilter
import java.io.IOException
import java.nio.charset.StandardCharsets

/**
 * 전역 예외 처리를 담당하는 필터 클래스입니다.
 * 애플리케이션에서 발생하는 예외를 캐치하여 적절한 HTTP 응답으로 변환합니다.
 *
 * @property objectMapper 예외 응답을 JSON으로 직렬화하기 위한 ObjectMapper 인스턴스
 */
class GlobalExceptionFilter(
    private val objectMapper: ObjectMapper
) : OncePerRequestFilter() {
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        try {
            filterChain.doFilter(request, response)
        } catch (e: CasperException) {
            writerErrorCode(response, e.errorCode)
        } catch (e: Exception) {
            e.printStackTrace()
            writerErrorCode(response, ErrorCode.INTERNAL_SERVER_ERROR)
        }
    }

    @Throws(IOException::class)
    private fun writerErrorCode(
        response: HttpServletResponse,
        errorCode: ErrorCode
    ) {
        val errorResponse = ErrorResponse(errorCode.status, errorCode.message)
        response.status = errorCode.status
        response.characterEncoding = StandardCharsets.UTF_8.name()
        response.contentType = MediaType.APPLICATION_JSON_VALUE
        response.writer.write(objectMapper.writeValueAsString(errorResponse))
    }
}
