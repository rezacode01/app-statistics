package com.tapsell.appstatistics

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cache.annotation.EnableCaching

@SpringBootApplication
@EnableCaching
class AppstatisticsApplication

fun main(args: Array<String>) {
    runApplication<AppstatisticsApplication>(*args)
}
