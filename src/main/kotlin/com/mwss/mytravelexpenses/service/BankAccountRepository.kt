package com.mwss.mytravelexpenses.service;

import com.mwss.mytravelexpenses.model.BankAccount
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.CrudRepository

interface BankAccountRepository : CrudRepository<BankAccount, Long> {
}