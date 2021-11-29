package com.mwss.mytravelexpenses.controllerMVC


import com.mwss.mytravelexpenses.model.User
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/")
class LoginController {

    @GetMapping
    fun getLoginPage(model: Model): String{
        model.addAttribute("title", "Welcome back to MyTravelExpenses")
        model.addAttribute("user", User())
        return "login"
    }

    // ToDo: add EndPoint to receive the data submitted and edit html file accordingly.
}