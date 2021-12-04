package com.mwss.mytravelexpenses.controllerMVC

import com.mwss.mytravelexpenses.model.Route
import com.mwss.mytravelexpenses.model.Trip
import com.mwss.mytravelexpenses.service.RouteRepository
import com.mwss.mytravelexpenses.service.TripRepository
import com.mwss.mytravelexpenses.service.UserRepository
import com.mwss.mytravelexpenses.utilities.AuthUtility
import com.mwss.mytravelexpenses.utilities.MessagingUtility
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*

@Controller
@RequestMapping("/mytrips")
class MyTripsController (
    private val authUtility: AuthUtility,
    private val messagingUtility: MessagingUtility,
    val tripRepository: TripRepository,
    val routeRepository: RouteRepository,
    val userRepository: UserRepository
        ) {

    @GetMapping("/dashboard")
    fun getMyTripsDashboard(
        model: Model,
        @CookieValue(value = "token", defaultValue = "") tokenUuidString: String
    ): String {
        // get user
        val user = authUtility.validateToken(tokenUuidString)

        // build model
        model.addAttribute("user", user)
        model.addAttribute("title", "${user.userName}'s trips")
        model.addAttribute("alert", messagingUtility.noAlert)
        model.addAttribute("route", Route())
        model.addAttribute("trip", Trip())

        return "myTrips"
    }

    @PostMapping("/add-route")
    fun addRoute(
        model: Model,
        @CookieValue(value = "token", defaultValue = "") tokenUuidString: String,
        @ModelAttribute newRoute: Route
    ): String {
        val user = authUtility.validateToken(tokenUuidString)

        //ToDo: Input validation?
        this.routeRepository.save(newRoute)

        user.routes.add(newRoute)

        this.userRepository.save(user)

        model.addAttribute("user", user)
        model.addAttribute("title", "${user.userName}'s trips")
        model.addAttribute("alert", messagingUtility.noAlert)
        model.addAttribute("route", Route())
        model.addAttribute("trip", Trip())

        return "myTrips"
    }
}
