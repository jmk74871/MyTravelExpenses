package com.mwss.mytravelexpenses.model

import javax.persistence.*

@Entity(name = "USER")
open class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID", nullable = false, unique = true)
    open var id: Long? = null

    @Column(name = "USER_NAME", nullable = false, unique = true)
    open var userName: String? = null

    @Column(name = "LAST_NAME", nullable = false)
    open var lastName: String? = null

    @Column(name = "FIRST_NAME", nullable = false)
    open var firstName: String? = null

    @Column(name = "ADDRESS", nullable = false)
    open var address: String? = null

    @Column(name = "PLZ", nullable = false)
    open var plz: String? = null

    @Column(name = "CITY", nullable = false)
    open var city: String? = null

    @OneToMany(cascade = [CascadeType.REMOVE], orphanRemoval = true)
    @JoinColumn(name = "USER_ID")
    open var trips: MutableList<Trip> = mutableListOf()

    @OneToMany(cascade = [CascadeType.REMOVE], orphanRemoval = true)
    @JoinColumn(name = "USER_ID")
    open var routes: MutableList<Route> = mutableListOf()

    @OneToMany(cascade = [CascadeType.REMOVE])
    @JoinColumn(name = "USER_ID")
    open var claims: MutableList<Claim> = mutableListOf()

    @OneToMany(cascade = [CascadeType.REMOVE],
        orphanRemoval = true)
    @JoinColumn(name = "USER_ID")
    open var bankAccounts: MutableList<BankAccount> = mutableListOf()
}