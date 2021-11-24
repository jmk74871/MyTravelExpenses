package com.mwss.mytravelexpenses.controller

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers

@WebMvcTest
internal class AuthControllerTest {

    @Autowired
    lateinit var mockMvc: MockMvc

    @Test
    fun getTest() {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/mtex/v1/auth/"))
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(MockMvcResultMatchers.content().string("test"))
    }
}