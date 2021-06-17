package io.pivotal.cna.demo.covid

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultHandlers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@SpringBootTest
@AutoConfigureMockMvc
class CovidReportControllerIT {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Test
    fun `GET reports without required params`() {
        mockMvc.perform(get("/api/v1/reports")).andDo(MockMvcResultHandlers.print())
                .andExpect(status().isBadRequest())
    }

    @Test
    fun `GET reports filter by date`() {
        val expectedContent = """
            [
              {
                "state": "Hubei",
                "region": "China",
                "date": "2020-03-20",
                "confirmed": 67800,
                "deaths": 3133,
                "recovered": 58382
              },
              {
                "admin": "",
                "state": "",
                "region": "Italy",
                "date": "2020-03-20",
                "confirmed": 47021,
                "deaths": 4032,
                "recovered": 4440
              }
            ]
        """.trimIndent()

        mockMvc.perform(get("/api/v1/reports?date=2020-03-20")).andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(content().json(expectedContent))
    }

    @Test
    fun `GET reports filter by region`() {
        val expectedContent = """
            [
              {
                "admin": "",
                "state": "",
                "region": "Italy",
                "date": "2020-03-20",
                "confirmed": 47021,
                "deaths": 4032,
                "recovered": 4440
              },
              {
                "admin": "",
                "state": "",
                "region": "Italy",
                "date": "2020-03-21",
                "confirmed": 53578,
                "deaths": 4825,
                "recovered": 6072
              },
              {
                "admin": "",
                "state": "",
                "region": "Italy",
                "date": "2020-03-22",
                "confirmed": 59138,
                "deaths": 5476,
                "recovered": 7024
              }
            ]
        """.trimIndent()

        mockMvc.perform(get("/api/v1/reports?region=italy")).andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(content().json(expectedContent))
    }

    @Test
    fun `GET reports filter by region and date`() {
        val expectedContent = """
            [
              {
                "admin": "",
                "state": "Hubei",
                "region": "China",
                "date": "2020-03-22",
                "confirmed": 67800,
                "deaths": 3144,
                "recovered": 59433
              }
            ]
        """.trimIndent()

        mockMvc.perform(get("/api/v1/reports?region=china&date=2020-03-22")).andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(content().json(expectedContent))
    }

    @Test
    fun `GET latest reports without required params`() {
        mockMvc.perform(get("/api/v1/reports/latest")).andDo(MockMvcResultHandlers.print())
                .andExpect(status().isBadRequest())
    }

    @Test
    fun `GET latest reports for a specific region`() {
        val expectedContent = """
              {
                "admin": "",
                "state": "",
                "region": "Singapore",
                "date": "2020-03-29",
                "confirmed": 844,
                "deaths": 3,
                "recovered": 212
              }
        """.trimIndent()

        mockMvc.perform(get("/api/v1/reports/latest?region=singapore")).andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(content().json(expectedContent))
    }
}