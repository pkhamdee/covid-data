package io.pivotal.cna.demo.covid

import com.opencsv.bean.CsvBindByName
import com.opencsv.bean.CsvToBeanBuilder
import com.opencsv.bean.HeaderColumnNameTranslateMappingStrategy
import com.opencsv.bean.MappingStrategy
import io.pivotal.cna.demo.covid.db.CovidDailyReportData
import io.pivotal.cna.demo.covid.db.CovidDailyReportDataRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.core.io.Resource
import org.springframework.stereotype.Service
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Service
class CovidDataParser {

    @Value("classpath:data/*")
    private lateinit var resources: Array<Resource>

    @Autowired
    lateinit var repository: CovidDailyReportDataRepository
    fun parse() {
        val entityRows = mutableListOf<CovidDailyReportData>()
        val csvFileNameFormat = DateTimeFormatter.ofPattern("MM-dd-yyyy'.csv'")

        for (resource in resources) {
            val date = LocalDate.parse(resource.filename, csvFileNameFormat)

            val csvRows = CsvToBeanBuilder<CovidCsvRow>(resource.file.bufferedReader())
                    .withType(CovidCsvRow::class.java)
                    .withMappingStrategy(getFieldMappingStrategy())
                    .build()
                    .parse()

            for (csvRow in csvRows) {
                val data = CovidDailyReportData(
                        admin = csvRow.admin,
                        state = csvRow.state,
                        region = csvRow.region,
                        date = date,
                        confirmed = csvRow.confirmed,
                        deaths = csvRow.deaths,
                        recovered = csvRow.recovered
                )
                entityRows.add(data)
            }
        }

        repository.saveAll(entityRows)
    }

    private fun getFieldMappingStrategy(): MappingStrategy<CovidCsvRow> {
        val strat = HeaderColumnNameTranslateMappingStrategy<CovidCsvRow>()
        strat.type = CovidCsvRow::class.java

        val mapping = HashMap<String, String>()

        // 03-21-2020 and prior
        mapping.put("Province/State", "state")
        mapping.put("Country/Region", "region")

        // 03-22-2020 and onward
        mapping.put("Province_State", "state")
        mapping.put("Country_Region", "region")
        mapping.put("Admin2", "admin")

        // Common
        mapping.put("Confirmed", "confirmed")
        mapping.put("Deaths", "deaths")
        mapping.put("Recovered", "recovered")

        strat.columnMapping = mapping

        return strat
    }
}

class CovidCsvRow {
    lateinit var state: String
    lateinit var region: String
    var admin: String = ""
    var confirmed: Int = 0
    var deaths: Int = 0
    var recovered: Int = 0

    override fun toString(): String {
        return "CovidCsvRow(state='$state', region='$region', admin='$admin', confirmed=$confirmed, deaths=$deaths, recovered=$recovered)"
    }
}