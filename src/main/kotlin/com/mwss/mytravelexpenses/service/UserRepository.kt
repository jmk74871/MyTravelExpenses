package com.mwss.mytravelexpenses.service;

import com.mwss.mytravelexpenses.model.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.CrudRepository

interface UserRepository : CrudRepository<User, Long> {
}