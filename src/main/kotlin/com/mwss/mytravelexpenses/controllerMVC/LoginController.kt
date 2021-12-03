package com.mwss.mytravelexpenses.controllerMVC


import com.mwss.mytravelexpenses.AlertObj
import com.mwss.mytravelexpenses.model.User
import com.mwss.mytravelexpenses.service.UserRepository
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException

@Controller
@RequestMapping("/")
class LoginController(
    val userRepo: UserRepository,
    val noAlert: AlertObj = AlertObj(false, "")
) {

    @GetMapping
    fun getLoginPage(model: Model): String{
        model.addAttribute("title", "Welcome back to MyTravelExpenses")
        model.addAttribute("user", User())
        model.addAttribute("alert", noAlert)
        return "login"
    }

    @PostMapping("/")
    fun loginUser(model: Model, @ModelAttribute user: User): String {
        val returnNotFound = ResponseStatusException(HttpStatus.BAD_REQUEST, "Wrong credentials")

        val userOptional = user.userName?.let { this.userRepo.findByUserNameIs(it) } ?: throw returnNotFound
        if (userOptional.isEmpty) throw returnNotFound
        val userFromDB = userOptional.get()

        user.password?.let { pwInput ->
            userFromDB.password?.let { pwFromDB ->
                if (pwFromDB != pwInput) throw returnNotFound
            }
        }

        model.addAttribute("title", "login")
        model.addAttribute("alert", AlertObj(true, "Login successful!"))
        return "login"
    }

    // ToDo: add EndPoint to receive the data submitted and edit html file accordingly.
}