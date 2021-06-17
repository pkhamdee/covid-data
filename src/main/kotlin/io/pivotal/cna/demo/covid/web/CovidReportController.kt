package io.pivotal.cna.demo.covid.web

import io.pivotal.cna.demo.covid.db.CovidDailyReportDataRepository
import io.pivotal.cna.demo.covid.db.toReportDataResponseItem
import io.pivotal.cna.demo.covid.db.toReportDataResponseItemList
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.format.annotation.DateTimeFormat
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDate

@RestController
class CovidReportController {
    @Autowired
    lateinit var repository: CovidDailyReportDataRepository

    @GetMapping("/api/v1/reports/latest")
    fun getLatestReport(@RequestParam region: String): ReportDataResponseItem {
        return repository.findFirstByRegionIgnoreCaseOrderByDateDesc(region).toReportDataResponseItem()
    }

    @GetMapping("/api/v1/reports", params = ["date"])
    fun getAllReportsForDate(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) date: LocalDate): List<ReportDataResponseItem> {
        return repository.findAllByDate(date).toReportDataResponseItemList()
    }

    @GetMapping("/api/v1/reports", params = ["region"])
    fun getAllReportsForDate(@RequestParam region: String): List<ReportDataResponseItem> {
        return repository.findAllByRegionIgnoreCase(region).toReportDataResponseItemList()
    }

    @GetMapping("/api/v1/reports", params = ["region", "date"])
    fun getAllReportsForRegionAndDate(
            @RequestParam region: String,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE
            ) date: LocalDate): List<ReportDataResponseItem> {
        return repository.findAllByRegionIgnoreCaseAndDate(region, date).toReportDataResponseItemList()
    }
}