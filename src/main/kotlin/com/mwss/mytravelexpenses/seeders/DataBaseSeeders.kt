package com.mwss.mytravelexpenses.seeders

import com.mwss.mytravelexpenses.model.User
import com.mwss.mytravelexpenses.service.UserRepository
import org.springframework.context.event.ContextRefreshedEvent
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component

@Component
class DataBaseSeeders (
    val userRepo: UserRepository
        ) {

    @EventListener
    fun dataSeeder(event: ContextRefreshedEvent){
        seedUserTabele()
    }

    fun seedUserTabele(){
        // add check and only add initial admin if needed
        val userData = listOf(
            listOf("jmkdev001", "Kuhlo", "Jonas", "Hauptstraße 23",  "72762", "Reutlingen"),
            listOf("jmkdev002", "Kuhlo", "Max", "Hauptstraße 23",  "72762", "Reutlingen"),
            listOf("jmkdev003", "Mkhayan", "Yulia", "Hauptstraße 23",  "72762", "Reutlingen")
        )
        for(dataSet in userData){
            val user = User()
            user.userName = dataSet[0]
            user.lastName = dataSet[1]
            user.firstName = dataSet[2]
            user.address = dataSet[3]
            user.plz = dataSet[4]
            user.city = dataSet[5]
            this.userRepo.save(user)
        }
    }
}