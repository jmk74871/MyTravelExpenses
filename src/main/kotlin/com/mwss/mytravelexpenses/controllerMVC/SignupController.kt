package com.mwss.mytravelexpenses.controllerMVC

import com.mwss.mytravelexpenses.model.User
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/signup")
class SignupController {

    @GetMapping("/")
    fun getSignupPage(model: Model): String{
        model.addAttribute("title", "Welcome to MyTravelExpenses")
        model.addAttribute("user", User())
        return "signup"
    }

    // ToDo: add EndPoint to receive the data submitted and edit html file accordingly.
}