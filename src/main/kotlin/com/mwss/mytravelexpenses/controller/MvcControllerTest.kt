package com.mwss.mytravelexpenses.controller

import com.mwss.mytravelexpenses.model.User
import com.mwss.mytravelexpenses.service.UserRepository
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/api/test")
class MvcControllerTest (
    val userRepo: UserRepository
        ){

    @GetMapping("/")
    fun mvcTestMapping(model: Model): String {
        model.addAttribute("title", "Hello User!")
        model.addAttribute("users", this.userRepo.findAll())
        return "index"
    }
}