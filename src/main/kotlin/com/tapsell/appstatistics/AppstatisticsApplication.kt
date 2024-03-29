package com.tapsell.appstatistics

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cache.annotation.EnableCaching

@SpringBootApplication
@EnableCaching
class AppStatisticsApplication

fun main(args: Array<String>) {
    runApplication<AppStatisticsApplication>(*args)
}
