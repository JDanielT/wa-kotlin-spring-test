package com.waproject.watest

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class WaTestApplication

fun main(args: Array<String>) {
	runApplication<WaTestApplication>(*args)
}
