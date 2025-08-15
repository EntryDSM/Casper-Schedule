package hs.kr.entrydsm.schedule.infrastructure.grpc.dto.response

import hs.kr.entrydsm.schedule.domain.schedule.model.type.Type

/**
 * 내부적으로 사용되는 스케줄 응답 데이터 클래스입니다.
 * 이 클래스는 도메인 계층과 인프라스트럭처 계층 간의 데이터 전달에 사용됩니다.
 *
 * @property type 스케줄의 유형 (시작일, 1차 발표일 등)
 * @property date 스케줄 날짜 (문자열 형식)
 */
data class InternalScheduleResponse(
    val type: Type,
    val date: String
)