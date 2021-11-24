package com.mwss.mytravelexpenses.model

import javax.persistence.*

@Entity(name = "ROUTE")
open class Route {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID", nullable = false, unique = true)
    open var id: Long? = null

    @Column(name = "START_LOCATION", nullable = false)
    open var startLocation: String? = null

    @Column(name = "END_LOCATION", nullable = false)
    open var endLocation: String? = null

    @Column(name = "DISTANCE_IN_KM", nullable = false)
    open var distanceInKm: Int = 0

}