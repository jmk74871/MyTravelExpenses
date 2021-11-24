package com.mwss.mytravelexpenses.model

import javax.persistence.*

@Table(name = "BANK_ACCOUNT")
@Entity(name = "BANKACCOUNT")
open class BankAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID", nullable = false)
    open var id: Long? = null

    @Column(name = "IBAN", nullable = false, unique = true)
    open var iban: String? = null

    @Column(name = "BIC", nullable = false)
    open var bic: String? = null

    @Column(name = "BANK_NAME", nullable = false)
    open var bankName: String? = null

}