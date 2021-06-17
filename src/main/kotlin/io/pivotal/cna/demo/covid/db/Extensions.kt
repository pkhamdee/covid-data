package io.pivotal.cna.demo.covid.db

import io.pivotal.cna.demo.covid.web.ReportDataResponseItem

fun Iterable<CovidDailyReportData>.toReportDataResponseItemList(): List<ReportDataResponseItem> {
    return this.map {
        it.toReportDataResponseItem()
    }.toList()
}

fun CovidDailyReportData.toReportDataResponseItem(): ReportDataResponseItem {
    return ReportDataResponseItem(
            admin = this.admin,
            state = this.state,
            region = this.region,
            date = this.date,
            confirmed = this.confirmed,
            deaths = this.deaths,
            recovered = this.recovered
    )
}