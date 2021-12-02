package com.mwss.mytravelexpenses.controllerMVC

import com.mwss.mytravelexpenses.model.User
import com.mwss.mytravelexpenses.service.UserRepository
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.server.ResponseStatusException

@Controller
@RequestMapping("/signup")
class SignupController (
    val userRepo: UserRepository
        ){

    @GetMapping("/")
    fun getSignupPage(model: Model): String{
        model.addAttribute("title", "Welcome to MyTravelExpenses")
        model.addAttribute("user", User())
        return "signup"
    }

    // ToDo: add EndPoint to receive the data submitted and edit html file accordingly.
}