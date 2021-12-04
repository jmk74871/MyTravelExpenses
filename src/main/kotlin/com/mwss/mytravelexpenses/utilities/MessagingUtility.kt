package com.mwss.mytravelexpenses.utilities

import com.mwss.mytravelexpenses.AlertObj
import org.hibernate.annotations.Comment
import org.springframework.stereotype.Component

@Component
class MessagingUtility {
    val noAlert = AlertObj(false, "")

    fun addedSuccess(objectDescription: String): AlertObj {
        return AlertObj(true, "Succesfully added $objectDescription")
    }
}