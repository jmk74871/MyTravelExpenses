package com.mwss.mytravelexpenses.controllerMVC

import com.mwss.mytravelexpenses.model.Route
import com.mwss.mytravelexpenses.service.RouteRepository
import com.mwss.mytravelexpenses.service.UserRepository
import com.mwss.mytravelexpenses.utilities.AuthUtility
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.CookieValue
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/myroutes")
class MyRoutesController (
    val routeRepository: RouteRepository,
    val userRepository: UserRepository,
    val authUtility: AuthUtility
        ){

    @PostMapping("/add")
    fun addRoute(model: Model, @CookieValue(value = "token", defaultValue = "") tokenUuidString: String, @RequestBody route: Route) {
        val user = authUtility.validateToken(tokenUuidString)

        //ToDo: Input validation?
        this.routeRepository.save(route)

        user.routes.add(route)

        this.userRepository.save(user)


    }
}