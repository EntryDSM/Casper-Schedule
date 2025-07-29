package hs.kr.entrydsm.schedule.global.security

import com.fasterxml.jackson.databind.ObjectMapper
import hs.kr.entrydsm.schedule.global.error.GlobalExceptionFilter
import hs.kr.entrydsm.schedule.global.security.jwt.JwtFilter
import org.springframework.security.config.annotation.SecurityConfigurerAdapter
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.web.DefaultSecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.stereotype.Component

/**
 * Spring Security 필터 체인을 구성하는 설정 클래스입니다.
 * JWT 인증 필터와 전역 예외 처리 필터를 적절한 위치에 등록합니다.
 *
 * @property objectMapper JSON 직렬화/역직렬화를 위한 ObjectMapper 인스턴스
 */
@Component
class FilterConfig(
    private val objectMapper: ObjectMapper
) : SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity>() {
    /**
     * Spring Security 필터 체인을 구성하는 메서드입니다.
     * 다음 필터들을 순서대로 등록합니다:
     * 1. GlobalExceptionFilter - 전역 예외 처리를 위한 필터
     * 2. JwtFilter - JWT 인증을 처리하는 필터
     * 3. UsernamePasswordAuthenticationFilter - 기본 인증 필터
     *
     * @param builder HttpSecurity 빌더 인스턴스
     * @throws Exception 필터 체인 구성 중 오류가 발생한 경우
     */
    @Throws(Exception::class)
    override fun configure(builder: HttpSecurity) {
        builder.addFilterBefore(
            JwtFilter(),
            UsernamePasswordAuthenticationFilter::class.java
        )
        builder.addFilterBefore(GlobalExceptionFilter(objectMapper), JwtFilter::class.java)
    }
}
