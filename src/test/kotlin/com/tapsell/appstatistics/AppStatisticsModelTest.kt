package com.tapsell.appstatistics

import com.tapsell.appstatistics.Model.AppStatistics
import com.tapsell.appstatistics.Repository.AppStatisticsRepo
import com.tapsell.appstatistics.Service.AppStatService
import com.tapsell.appstatistics.Utlis.toGregorian
import org.bson.types.ObjectId
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

@RunWith(SpringRunner::class)
@SpringBootTest
class AppStatisticsTest {

    @Autowired
    lateinit var appStatisticsRepo: AppStatisticsRepo

    @Test
    fun createAppStatistics() {
        var objId = ObjectId().toString()

        var newObj = AppStatistics(
                id = objId,
                reportTime = GregorianCalendar(2018, 11, 28).time,
                type = Random(0).nextInt(10),
                videoClicks = Random(0).nextInt(1000),
                videoInstalls = Random(0).nextInt(1000)
        )

        appStatisticsRepo.save(newObj)
        var retObj = appStatisticsRepo.findById(objId)
        if (! retObj.isEmpty) {
            assert(retObj.get().type == newObj.type)
        }
    }

    @Test
    fun findByDateAndTypeTest() {
        val appStatisticsList = appStatisticsRepo.findByTypeAndReportTimeBetween(
                1,
                startDate = GregorianCalendar(2011, 11, 28).time,
                endDate = GregorianCalendar(2018, 11, 28).time
        )

        for (appS in appStatisticsList) {
            println("AppStatistics with id ${appS.id} has type ${appS.type}")
        }
    }

    @Test
    fun convertPersianDateToGregorian() {
        val date = "1398-04-12"
        val greDate = date.toGregorian()
        println(greDate)
    }
}