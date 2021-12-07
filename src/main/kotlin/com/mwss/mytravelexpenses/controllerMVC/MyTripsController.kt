package com.mwss.mytravelexpenses.controllerMVC

import com.mwss.mytravelexpenses.AlertObj
import com.mwss.mytravelexpenses.model.Route
import com.mwss.mytravelexpenses.model.Trip
import com.mwss.mytravelexpenses.model.User
import com.mwss.mytravelexpenses.service.RouteRepository
import com.mwss.mytravelexpenses.service.TripRepository
import com.mwss.mytravelexpenses.service.UserRepository
import com.mwss.mytravelexpenses.utilities.AuthUtility
import com.mwss.mytravelexpenses.utilities.MessagingUtility
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
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
        buildModel(user, model, messagingUtility.noAlert)

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

        // save
        this.routeRepository.save(newRoute)
        user.routes.add(newRoute)
        this.userRepository.save(user)

        buildModel(user, model, messagingUtility.noAlert)

        return "myTrips"
    }

    @PostMapping("/add-trip-to-route")
    fun addTripToRoute(
        model: Model,
        @CookieValue(value = "token", defaultValue = "") tokenUuidString: String,
        @RequestParam("routeId") routeId: String,
        @ModelAttribute newTrip: Trip
    ): String {
        val user = this.authUtility.validateToken(tokenUuidString)

        if( routeId.all { it in '0'..'9' }){
            val routeOptional = this.routeRepository.findById(routeId.toLong())
            if(routeOptional.isPresent) newTrip.routes.add(routeOptional.get())
        }


        buildModel(user, model, messagingUtility.noAlert)
        model["trip"] = newTrip

        return "myTrips"
    }

    fun buildModel(user: User, model: Model, alert: AlertObj): Model {
        model["user"]=user
        model["title"] = "${user.userName}'s trips"
        model.addAttribute("alert", alert)
        model.addAttribute("route", Route())
        model.addAttribute("trip", Trip())
        return model
    }
}
