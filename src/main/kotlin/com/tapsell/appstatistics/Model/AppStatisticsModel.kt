package com.tapsell.appstatistics.Model

class AppStatisticsModel (
        val weekNum: Int,
        val year: Int,
        var requests: Int = 0,
        var clicks: Int = 0,
        var installs: Int = 0
) {
    fun addStatistics(requests: Int, clicks: Int, installs: Int) : AppStatisticsModel{
        this.requests += requests
        this.clicks += clicks
        this.installs += installs

        return this
    }
}