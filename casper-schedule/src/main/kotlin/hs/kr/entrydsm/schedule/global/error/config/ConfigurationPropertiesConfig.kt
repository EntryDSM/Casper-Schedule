package hs.kr.entrydsm.schedule.global.error.config

import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.context.annotation.Configuration

/**
 * 설정 속성 클래스들을 스캔하기 위한 구성 클래스입니다.
 * 'hs.kr.entrydsm.schedule' 패키지 하위의 @ConfigurationProperties 어노테이션이 붙은 클래스들을 스프링 빈으로 등록합니다.
 */
@ConfigurationPropertiesScan("hs.kr.entrydsm.schedule")
@Configuration
class ConfigurationPropertiesConfig
