package io.pivotal.cna.demo.covid.db

import java.time.LocalDate
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
data class CovidDailyReportData(
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        var id: Int = 0,
        val admin: String,
        val state: String,
        val region: String,
        val date: LocalDate,
        var confirmed: Int = 0,
        var deaths: Int = 0,
        var recovered: Int = 0
)