package com.mwss.mytravelexpenses.controllerMVC

import com.mwss.mytravelexpenses.AlertObj
import com.mwss.mytravelexpenses.utilities.AuthUtility
import com.mwss.mytravelexpenses.utilities.MessagingUtility
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.CookieValue
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping


@Controller
@RequestMapping("/dashboard")
class DashboardController (
    val authUtility: AuthUtility,
    val messagingUtility: MessagingUtility
        ){

    @GetMapping("/")
    fun getDashboard(model: Model, @CookieValue(value = "token", defaultValue = "") tokenUuidString: String): String{

        val user = authUtility.validateToken(tokenUuidString)


        model.addAttribute("user", user)
        model.addAttribute("title", "Dashboard")
        model.addAttribute("alert", this.messagingUtility.noAlert)
        return "dashboard"
    }
}