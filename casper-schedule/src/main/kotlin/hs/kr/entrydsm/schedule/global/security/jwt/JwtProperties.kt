package hs.kr.entrydsm.schedule.global.security.jwt

import org.springframework.boot.context.properties.ConfigurationProperties

/**
 * JWT 관련 설정 속성을 관리하는 클래스입니다.
 * application.yml 파일에서 'auth.jwt' 하위의 설정 값을 주입받습니다.
 *
 * @property secretKey JWT 서명에 사용되는 비밀 키
 * @property header JWT 토큰이 전달될 HTTP 헤더 이름
 * @property prefix JWT 토큰의 접두사 (예: 'Bearer ')
 */
@ConfigurationProperties("jwt")
class JwtProperties(
    val secretKey: String,
    val header: String,
    val prefix: String
)
