package com.tapsell.appstatistics

import com.tapsell.appstatistics.Model.AppStatisticsListResponse
import com.tapsell.appstatistics.Model.AppStatisticsModel
import com.tapsell.appstatistics.Repository.AppStatisticsRepo
import com.tapsell.appstatistics.Service.AppStatService
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.stereotype.Service
import org.springframework.test.context.junit4.SpringRunner
import java.util.*

@RunWith(SpringRunner::class)
@SpringBootTest
class AppStatsServiceTest {


    @Autowired
    lateinit var appStatsService: AppStatService

    @Test
    fun checkAppStatsServiceLayer() {
        appStatsService.getStats(
                startDate = GregorianCalendar(2011, 11, 28).time,
                endDate = GregorianCalendar(2018, 11, 28).time,
                type = 1
        )
    }
}

@Service
class AppStatsServiceStub : AppStatService{

    @Autowired
    lateinit var appStatisticsRepo: AppStatisticsRepo

    override fun getStats(startDate: Date, endDate: Date, type: Int): AppStatisticsListResponse {

        val appStatisticsList = appStatisticsRepo.findByTypeAndReportTimeBetween(type, startDate, endDate)

        return AppStatisticsListResponse(List(1) { AppStatisticsModel(10, 300) })
    }
}