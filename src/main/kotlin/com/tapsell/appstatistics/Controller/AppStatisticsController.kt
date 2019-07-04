package com.tapsell.appstatistics.Controller

import com.tapsell.appstatistics.Model.AppStatisticsListResponse
import com.tapsell.appstatistics.Service.AppStatService
import com.tapsell.appstatistics.Utlis.toGregorian
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
class AppStatisticsController {

    @Autowired
    lateinit var appStatsService: AppStatService

    @GetMapping("/app-statistics")
    fun getAppStatsByTypeAndDate (
            @RequestParam(required = false) startdate: String,
            @RequestParam(required = false) enddate: String,
            @RequestParam(required = false, defaultValue = 1.toString()) type: Int) : AppStatisticsListResponse {

        println("$startdate, $enddate")

        return appStatsService.getStats(startdate.toGregorian(), enddate.toGregorian(), type)
    }
}