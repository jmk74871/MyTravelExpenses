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
import java.time.LocalDateTime

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

    @PostMapping("/add-trip")
    fun addTrip(
        model: Model,
        @CookieValue(value = "token", defaultValue = "") tokenUuidString: String,
        @RequestParam("startDate") startDateString: String,
        @RequestParam("endDate") endDateString: String
    ):String{
        val user = this.authUtility.validateToken(tokenUuidString)

        val newTrip = Trip()

        newTrip.startDateTime = LocalDateTime.parse(startDateString)
        newTrip.endDateTime = LocalDateTime.parse(endDateString)

        user.trips.add(0, this.tripRepository.save(newTrip))
        this.userRepository.save(user)

        buildModel(user, model, messagingUtility.noAlert)

        return "myTrips"
    }

    @PostMapping("/add-route-to-trip")
    fun addRouteToTrip(
        model: Model,
        @CookieValue(value = "token", defaultValue = "") tokenUuidString: String,
        @RequestParam("routeId") routeId: String,
        @RequestParam("tripId") tripId: String
    ): String {
        val user = this.authUtility.validateToken(tokenUuidString)

        // check input
        if(!(tripId.all { it in '0'..'9' } && routeId.all { it in '0'..'9' })){
            buildModel(user, model, messagingUtility.faultyInput)
            return "myTrips"
        }

        //get trip from id
        val trip: Trip = user.findTripById(tripId.toLong()) ?: run{
            buildModel(user, model, messagingUtility.objNotFound)
            return "myTrips" }

        //get route from id
        val route = user.findRouteById(routeId.toLong()) ?: run{
            buildModel(user, model, messagingUtility.objNotFound)
            return "myTrips" }

        // add route and save trip
        trip.routes.add(route)
        this.tripRepository.save(trip)
        // this.userRepository.save(user)

        //build model and return
        buildModel(user, model, messagingUtility.noAlert)
        return "myTrips"

    }

    // ToDo: add remove route endpoint

    fun buildModel(user: User, model: Model, alert: AlertObj): Model {
        model["user"]=user
        model["title"] = "${user.userName}'s trips"
        model.addAttribute("alert", alert)
        model.addAttribute("route", Route())
        model.addAttribute("trip", Trip())
        return model
    }
}
