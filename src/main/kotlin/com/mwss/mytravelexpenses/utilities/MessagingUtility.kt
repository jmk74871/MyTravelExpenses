package com.mwss.mytravelexpenses.utilities

import com.mwss.mytravelexpenses.AlertObj
import org.hibernate.annotations.Comment
import org.springframework.stereotype.Component

@Component
class MessagingUtility {
    val noAlert = AlertObj(false, "")

    val faultyInput = AlertObj(true, "Could not read input properly try again or contact admin if operation fails repeatedly.")

    val objNotFound = AlertObj(true, "Could not find some of the entities mentioned. Try again!")

    fun addedSuccess(objectDescription: String): AlertObj {
        return AlertObj(true, "Succesfully added $objectDescription")
    }
}