package com.mwss.mytravelexpenses.service;

import com.mwss.mytravelexpenses.model.Token
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface TokenRepository : JpaRepository<Token, Long> {

    fun findByUuidStringIs(uuidString: String): Optional<Token>

}