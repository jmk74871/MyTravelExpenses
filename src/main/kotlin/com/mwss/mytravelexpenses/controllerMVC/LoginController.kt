package com.mwss.mytravelexpenses.controllerMVC


import com.mwss.mytravelexpenses.AlertObj
import com.mwss.mytravelexpenses.model.Token
import com.mwss.mytravelexpenses.model.User
import com.mwss.mytravelexpenses.service.TokenRepository
import com.mwss.mytravelexpenses.service.UserRepository
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException
import java.time.Instant
import java.util.*
import javax.servlet.http.Cookie
import javax.servlet.http.HttpServletResponse

@Controller
@RequestMapping("/")
class LoginController(
    val userRepo: UserRepository,
    val tokenRepo: TokenRepository
) {

    val noAlert: AlertObj = AlertObj(false, "")

    @GetMapping
    fun getLoginPage(model: Model): String{
        model.addAttribute("title", "Welcome back to MyTravelExpenses")
        model.addAttribute("user", User())
        model.addAttribute("alert", noAlert)
        return "login"
    }

    @PostMapping("/")
    fun loginUser(model: Model, @ModelAttribute user: User, response: HttpServletResponse): String {
        val returnNotFound = ResponseStatusException(HttpStatus.BAD_REQUEST, "Wrong credentials")

        // get user from db
        val userOptional = user.userName?.let { this.userRepo.findByUserNameIs(it) } ?: throw returnNotFound
        if (userOptional.isEmpty) throw returnNotFound
        val userFromDB = userOptional.get()

        // check the password
        user.password?.let { pwInput ->
            userFromDB.password?.let { pwFromDB ->
                if (pwFromDB != pwInput) throw returnNotFound
            }
        }

        // generate token an set a cookie
        response.addCookie(generateToken(userFromDB))

        model.addAttribute("title", "Dashboard")
        model.addAttribute("alert", AlertObj(true, "Login successful!"))
        return "dashboard"
    }

    fun generateToken(user: User): Cookie {
        // get a token Object
        val newToken = Token()

        // set values
        newToken.user = user
        newToken.uuidString = UUID.randomUUID().toString()
        newToken.timeOut = Instant.EPOCH.epochSecond + 601

        this.tokenRepo.save(newToken)

        val cookie = Cookie("token", newToken.uuidString)
        cookie.maxAge = 600
        cookie.path = "/"
        return cookie
    }
}