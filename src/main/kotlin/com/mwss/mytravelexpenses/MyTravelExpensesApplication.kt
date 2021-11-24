package com.mwss.mytravelexpenses

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.web.context.WebApplicationContext

@SpringBootApplication
class MyTravelExpensesApplication

fun main(args: Array<String>) {
    runApplication<MyTravelExpensesApplication>(*args)
}
