package com.mwss.mytravelexpenses

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.event.ContextRefreshedEvent
import org.springframework.context.event.EventListener
import org.springframework.web.context.WebApplicationContext

@SpringBootApplication
class MyTravelExpensesApplication

    fun main(args: Array<String>) {
    runApplication<MyTravelExpensesApplication>(*args)
}


