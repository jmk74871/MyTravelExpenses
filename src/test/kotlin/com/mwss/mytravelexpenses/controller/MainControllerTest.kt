package com.mwss.mytravelexpenses.controller

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers

@WebMvcTest
internal class MainControllerTest {

    @Autowired
    lateinit var mockMvc: MockMvc

    @Test
    fun getTest() {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/mtex/v1/"))
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(MockMvcResultMatchers.content().string("test"))
    }
}