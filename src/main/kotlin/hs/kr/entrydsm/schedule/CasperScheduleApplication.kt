package hs.kr.entrydsm.schedule

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

/**
 * Casper Schedule 서비스의 메인 애플리케이션 클래스입니다.
 * Spring Boot 애플리케이션의 진입점입니다.
 *
 * @constructor CasperScheduleApplication의 새 인스턴스를 생성합니다.
 */
@SpringBootApplication
class CasperScheduleApplication

/**
 * Spring Boot 애플리케이션을 시작하는 메인 함수입니다.
 *
 * @param args 애플리케이션에 전달된 커맨드 라인 인수
 */
fun main(args: Array<String>) {
    runApplication<CasperScheduleApplication>(*args)
}
