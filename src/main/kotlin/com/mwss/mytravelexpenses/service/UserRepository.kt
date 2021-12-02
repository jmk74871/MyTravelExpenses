package com.mwss.mytravelexpenses.service;

import com.mwss.mytravelexpenses.model.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.CrudRepository
import java.util.*

interface UserRepository : CrudRepository<User, Long> {
    fun findByUserNameIs(userName: String):Optional<User>
}