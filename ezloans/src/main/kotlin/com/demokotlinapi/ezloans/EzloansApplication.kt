package com.demokotlinapi.ezloans

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class EzloansApplication

fun main(args: Array<String>) {
	runApplication<EzloansApplication>(*args)
}
