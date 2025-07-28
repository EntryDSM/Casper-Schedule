package hs.kr.entrydsm.schedule

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

/**
 * Casper Schedule 애플리케이션의 진입점입니다.
 * Spring Boot 애플리케이션을 부트스트랩하고 실행하는 클래스입니다.
 */
@SpringBootApplication
class CasperScheduleApplication

/**
 * 애플리케이션의 진입점입니다.
 * Spring Boot 애플리케이션을 실행합니다.
 *
 * @param args 명령줄 인수 배열
 */
fun main(args: Array<String>) {
	runApplication<CasperScheduleApplication>(*args)
}
