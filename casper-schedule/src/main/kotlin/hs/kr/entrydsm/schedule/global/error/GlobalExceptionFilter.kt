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
    /**
     * 모든 요청에 대해 필터를 적용하여 예외 처리를 수행합니다.
     *
     * @param request HTTP 요청 객체
     * @param response HTTP 응답 객체
     * @param filterChain 다음 필터 또는 서블릿으로 요청을 전달하기 위한 체인
     * @throws Exception 처리 중 발생할 수 있는 예외
     */
    @Throws(Exception::class)
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

    /**
     * 에러 코드에 해당하는 HTTP 응답을 작성합니다.
     *
     * @param response HTTP 응답 객체
     * @param errorCode 에러 코드 (상태 코드와 메시지 포함)
     * @throws IOException 응답 작성 중 발생할 수 있는 I/O 예외
     */
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
