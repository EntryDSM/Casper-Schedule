package hs.kr.entrydsm.schedule.global.security.jwt

import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.web.filter.OncePerRequestFilter

/**
 * JWT 인증을 처리하는 필터 클래스입니다.
 * HTTP 요청 헤더에서 사용자 ID와 역할을 추출하여 Spring Security의 SecurityContext에 인증 정보를 설정합니다.
 * 인증이 필요한 API에 대한 접근 제어를 담당합니다.
 */
class JwtFilter : OncePerRequestFilter() {
    /**
     * HTTP 요청을 가로채서 JWT 인증을 처리하는 메서드입니다.
     * 'Request-User-Id'와 'Request-User-Role' 헤더에서 사용자 정보를 추출하여 SecurityContext에 인증 정보를 설정합니다.
     *
     * @param request HTTP 요청 객체
     * @param response HTTP 응답 객체
     * @param filterChain 다음 필터 체인을 실행하기 위한 객체
     * @throws jakarta.servlet.ServletException 서블릿 예외가 발생한 경우
     * @throws java.io.IOException 입출력 예외가 발생한 경우
     */
    @Throws(jakarta.servlet.ServletException::class, java.io.IOException::class)
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val userId: String? = request.getHeader("Request-User-Id")
        val role: UserRole? = request.getHeader("Request-User-Role")?.let { UserRole.valueOf(it) }

        if ((userId == null) || (role == null)) {
            filterChain.doFilter(request, response)
            return
        }

        val authorities = mutableListOf(SimpleGrantedAuthority("ROLE_${role.name}"))
        val userDetails: UserDetails = User(userId, "", authorities)
        val authentication: Authentication =
            UsernamePasswordAuthenticationToken(userDetails, "", userDetails.authorities)

        SecurityContextHolder.getContext().authentication = authentication
        filterChain.doFilter(request, response)
    }
}

/**
 * 애플리케이션에서 사용되는 사용자 역할을 정의한 열거형 클래스입니다.
 * 각 역할은 애플리케이션 내에서의 권한 수준을 나타냅니다.
 *
 * @property ROOT 최고 관리자 권한 (시스템 전체 관리)
 * @property CONFIRM_APPLICATION 지원서 확인 권한 (입학처 직원 등)
 * @property USER 일반 사용자 권한 (지원자 등)
 * @property ADMIN 관리자 권한 (일반 관리자)
 */
enum class UserRole {
    ROOT,
    CONFIRM_APPLICATION,
    USER,
    ADMIN
}
