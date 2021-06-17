package io.pivotal.cna.demo.covid

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.stereotype.Service

@SpringBootApplication
class CovidApplication

fun main(args: Array<String>) {
	runApplication<CovidApplication>(*args)
}

@Service
class DataBootstrap: ApplicationRunner {
	
	@Autowired
	private lateinit var dataParser: CovidDataParser

	override fun run(args: ApplicationArguments?) {
		dataParser.parse()
	}
}