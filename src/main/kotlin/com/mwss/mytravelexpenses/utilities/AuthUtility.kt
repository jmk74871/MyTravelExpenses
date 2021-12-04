package com.mwss.mytravelexpenses.utilities

import com.mwss.mytravelexpenses.model.User
import com.mwss.mytravelexpenses.service.TokenRepository
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component
import org.springframework.web.server.ResponseStatusException

@Component
class AuthUtility (
    val tokenRepository: TokenRepository
        ) {

    fun validateToken(uuidString: String): User {
            val tokenOptional = this.tokenRepository.findByUuidStringIs(uuidString)
            if(tokenOptional.isEmpty) throw ResponseStatusException(HttpStatus.BAD_REQUEST, "no valid token provided")

            tokenOptional.get().user?.let { return it } ?: throw ResponseStatusException(HttpStatus.BAD_REQUEST, "no valid token provided")
        }
}