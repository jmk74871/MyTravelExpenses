package com.mwss.mytravelexpenses.service;

import com.mwss.mytravelexpenses.model.Claim
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.CrudRepository

interface ClaimRepository : CrudRepository<Claim, Long> {
}