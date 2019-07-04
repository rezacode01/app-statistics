package com.tapsell.appstatistics.Model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.util.Date

@Document(collection = "AppStatistics")
class AppStatistics (
        @Id val id: String,
        val reportTime: Date,
        val type: Int,
        val videoRequests: Int = 0,
        val webViewRequests: Int = 0,
        val videoClicks: Int = 0,
        val webViewClicks: Int = 0,
        val videoInstalls: Int = 0,
        val webViewInstalls: Int = 0
) {

    fun getTotalRequest() : Int {
        return videoRequests + webViewRequests
    }

    fun getTotalClicks() : Int {
        return videoClicks + webViewClicks
    }

    fun getTotalInstalls(): Int {
        return videoInstalls + webViewInstalls
    }
}