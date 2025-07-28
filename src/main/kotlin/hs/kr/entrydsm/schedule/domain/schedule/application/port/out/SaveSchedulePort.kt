package hs.kr.entrydsm.schedule.domain.schedule.application.port.out

import hs.kr.entrydsm.schedule.domain.schedule.model.Schedule

/**
 * 일정을 저장하는 포트 인터페이스입니다.
 */
interface SaveSchedulePort {
    /**
     * 일정을 저장합니다.
     *
     * @param schedule 저장할 일정 도메인 모델
     * @return 저장된 일정 도메인 모델
     */
    fun saveSchedule(schedule: Schedule): Schedule
}
