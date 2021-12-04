package com.mwss.mytravelexpenses.controllerMVC

import com.mwss.mytravelexpenses.utilities.AuthUtility
import com.mwss.mytravelexpenses.utilities.MessagingUtility
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.CookieValue
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping


@Controller
@RequestMapping("/myaccount")
class MyAccountController (
    val authUtility: AuthUtility,
    val messagingUtility: MessagingUtility
        ) {

    @GetMapping("/")
    fun getAccountDetails(model: Model, @CookieValue(value = "token", defaultValue = "") tokenUuidString: String): String{
        val user = authUtility.validateToken(tokenUuidString)

        model.addAttribute("user", user)
        model.addAttribute("title", "My Account Details")
        model.addAttribute("alert", messagingUtility.noAlert)

        return "myAccount"
    }

    // ToDo: add endpoint to receive updates to the account details
}