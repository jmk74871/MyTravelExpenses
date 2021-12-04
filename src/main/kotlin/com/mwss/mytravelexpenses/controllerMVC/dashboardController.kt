package com.mwss.mytravelexpenses.controllerMVC

import com.mwss.mytravelexpenses.AlertObj
import com.mwss.mytravelexpenses.service.TokenRepository
import com.mwss.mytravelexpenses.utilities.AuthUtility
import org.springframework.boot.Banner
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.CookieValue
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping


@Controller
@RequestMapping("/dashboard")
class dashboardController (
    val authUtility: AuthUtility
        ){

    val noAlert: AlertObj = AlertObj(false, "")

    @GetMapping("/")
    fun getDashboard(model: Model, @CookieValue(value = "token", defaultValue = "") tokenUuidString: String): String{

        val user = authUtility.validateToken(tokenUuidString)

        model.addAttribute("trips", user.trips.take(3))
        model.addAttribute("claims", user.claims.take(3))

        model.addAttribute("title", "Dashboard")
        model.addAttribute("alert", noAlert)
        return "dashboard"
    }
}