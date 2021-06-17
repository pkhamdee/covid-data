package io.pivotal.cna.demo.covid.web

import java.time.LocalDate

data class ReportDataResponseItem(
        val admin: String,
        val state: String,
        val region: String,
        val date: LocalDate,
        var confirmed: Int,
        var deaths: Int,
        var recovered: Int
)