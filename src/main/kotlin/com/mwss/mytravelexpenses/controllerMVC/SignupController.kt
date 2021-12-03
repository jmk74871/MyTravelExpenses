package com.mwss.mytravelexpenses.controllerMVC

import com.mwss.mytravelexpenses.model.User
import com.mwss.mytravelexpenses.service.UserRepository
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException

@Controller
@RequestMapping("/signup")
class SignupController (
    val userRepo: UserRepository
        ){

    @GetMapping("/")
    fun getSignupPage(model: Model): String{
        model.addAttribute("title", "Welcome to MyTravelExpenses")
        model.addAttribute("user", User())
        return "signup"
    }

    @PostMapping("/")
    fun addUserAccount(model: Model, @ModelAttribute user: User): String {

        val returnMissingInput = ResponseStatusException(HttpStatus.BAD_REQUEST, "required input missing.")
        // check input
        user.userName?.let { if (it == "") throw returnMissingInput } ?: throw returnMissingInput
        user.firstName?.let { if (it == "") throw returnMissingInput } ?: throw returnMissingInput
        user.lastName?.let { if (it == "") throw returnMissingInput } ?: throw returnMissingInput
        user.address?.let { if (it == "") throw returnMissingInput } ?: throw returnMissingInput
        user.plz?.let { if (it == "") throw returnMissingInput } ?: throw returnMissingInput
        user.city?.let { if (it == "") throw returnMissingInput } ?: throw returnMissingInput

        // check password complexity
        user.password?.let {pw -> if (pw.none { char -> char in 'A'..'Z' } || pw.none { char -> char in 'a'..'z' } ||  pw.none { char -> char in '0'..'9' } || pw.length < 8)
            throw ResponseStatusException(HttpStatus.BAD_REQUEST, "password complexity requirements not met") } ?: throw returnMissingInput

        //save to db
        try {
            this.userRepo.save(user)
        }catch (e: Exception){
            throw ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.toString())
        }

        model.addAttribute("title", "Success")
        return "success"
    }

    // ToDo: add EndPoint to receive the data submitted and edit html file accordingly.
}