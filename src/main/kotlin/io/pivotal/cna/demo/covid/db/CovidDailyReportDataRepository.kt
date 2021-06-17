package io.pivotal.cna.demo.covid.db

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.time.LocalDate

@Repository
interface CovidDailyReportDataRepository : CrudRepository<CovidDailyReportData, Int> {
    fun findAllByDate(date: LocalDate): List<CovidDailyReportData>
    fun findFirstByRegionIgnoreCaseOrderByDateDesc(region: String): CovidDailyReportData
    fun findAllByRegionIgnoreCase(region: String): List<CovidDailyReportData>
    fun findAllByRegionIgnoreCaseAndDate(region: String, date: LocalDate): List<CovidDailyReportData>
}