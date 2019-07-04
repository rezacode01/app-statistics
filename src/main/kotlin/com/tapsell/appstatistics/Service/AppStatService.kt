package com.tapsell.appstatistics.Service

import com.tapsell.appstatistics.Model.AppStatisticsListResponse
import com.tapsell.appstatistics.Model.AppStatisticsModel
import com.tapsell.appstatistics.Repository.AppStatisticsRepo
import com.tapsell.appstatistics.Utlis.toPersianWeek
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.cache.annotation.Cacheable
import org.springframework.stereotype.Service
import java.util.*

interface AppStatService {
    fun getStats(startDate: Date, endDate: Date, type: Int): AppStatisticsListResponse
}

@Service("appStatsService")
class AppStatServiceImpl : AppStatService {

    @Autowired
    lateinit var appStatisticsRepo: AppStatisticsRepo

    @Cacheable(value=["AppStatisticsWeeklyReport"], key = "#startDate-#endDate-#type")
    override fun getStats(startDate: Date, endDate: Date, type: Int): AppStatisticsListResponse {
        val appStatisticsList = appStatisticsRepo.findByTypeAndReportTimeBetween(type, startDate, endDate)

        val appStatisticsModelsList = appStatisticsList.groupingBy { it.reportTime.toPersianWeek() }
                .aggregate { key, accumulator: AppStatisticsModel?, element, first ->
                    if (first) {
                        AppStatisticsModel(
                                key,
                                element.reportTime.toPersianWeek(),
                                element.getTotalRequest(),
                                element.getTotalClicks(),
                                element.getTotalInstalls()

                        )
                    }
                    else {
                        accumulator!!.addStatistics(
                                element.getTotalRequest(),
                                element.getTotalClicks(),
                                element.getTotalInstalls()
                        )
                    }
                }.values.mapNotNull { it }.sortedWith(compareBy({ it.year }, { it.weekNum }))

        return AppStatisticsListResponse(appStatisticsModelsList)

    }
}