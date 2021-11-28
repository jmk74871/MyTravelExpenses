package com.mwss.mytravelexpenses.controller

import com.mwss.mytravelexpenses.service.*
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/mtex/v1")
class MainController (
//    val bankRepo: BankAccountRepository,
//    val claimRepo: ClaimRepository,
//    val routeRepo: RouteRepository,
//    val tripRepo: TripRepository,
//    val userRepo: UserRepository
        ){

    @GetMapping("/")
    fun getTest(): String{
        return "test"
    }

}