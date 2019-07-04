package com.tapsell.appstatistics.Repository

import com.tapsell.appstatistics.Model.AppStatistics
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface AppStatisticsRepo :CrudRepository<AppStatistics, String> {
    fun findByTypeAndReportTimeBetween(type: Int, startDate: Date, endDate: Date) : List<AppStatistics>
}