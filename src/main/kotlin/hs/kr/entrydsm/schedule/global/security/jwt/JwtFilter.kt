package hs.kr.entrydsm.schedule.global.security.jwt

import io.lettuce.core.tracing.Tracing.clearContext
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

        clearContext()
        SecurityContextHolder.getContext().authentication = authentication
        filterChain.doFilter(request, response)
    }
}

enum class UserRole {
    ROOT,
    CONFIRM_APPLICATION,
    USER,
    ADMIN
}
