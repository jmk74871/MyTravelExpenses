package com.mwss.mytravelexpenses.controller

import org.hamcrest.CoreMatchers.containsString
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers

@WebMvcTest(MvcControllerTest::class)
internal class MvcControllerTestTest {

    @Autowired
    lateinit var mockMvc: MockMvc

    @Test
    fun mvcTestMapping() {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/test/"))
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(MockMvcResultMatchers.view().name("index"))
            .andExpect(MockMvcResultMatchers.content().string(containsString("Hello World!")))
    }
}