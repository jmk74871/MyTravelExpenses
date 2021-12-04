package com.mwss.mytravelexpenses.controllerMVC

import com.mwss.mytravelexpenses.AlertObj
import com.mwss.mytravelexpenses.utilities.AuthUtility
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.CookieValue
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping


@Controller
@RequestMapping("/myaccount")
class myAccountController (
    val authUtility: AuthUtility
        ) {

    val noAlert = AlertObj(false, "")

    @GetMapping("/")
    fun getAccountDetails(model: Model, @CookieValue(value = "token", defaultValue = "") tokenUuidString: String): String{
        val user = authUtility.validateToken(tokenUuidString)

        model.addAttribute("user", user)
        model.addAttribute("title", "My Account Details")
        model.addAttribute("alert", noAlert)

        return "myAccount"

    }
}