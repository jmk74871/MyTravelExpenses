package com.mwss.mytravelexpenses.model

import javax.persistence.*

@Entity(name = "CLAIM")
open class Claim {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID", nullable = false, unique = true)
    open var id: Long? = null

    @Enumerated(EnumType.STRING)
    @Column(name = "CLAIM_STATUS", nullable = false)
    open var claimStatus: ClaimStatusEnum? = null


    @ManyToMany(cascade = [CascadeType.PERSIST])
    @JoinTable(name = "CLAIM_ROUTES",
        joinColumns = [JoinColumn(name = "CLAIM_ID")],
        inverseJoinColumns = [JoinColumn(name = "ROUTE_ID")])
    open var routes: MutableList<Route> = mutableListOf()

}