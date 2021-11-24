package com.mwss.mytravelexpenses.model

import java.time.LocalDate
import java.time.LocalDateTime
import javax.persistence.*

@Entity(name = "TRIP")
open class Trip {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID", nullable = false, unique = true)
    open var id: Long? = null

    @Column(name = "START_DATE_TIME", nullable = false)
    open var startDateTime: LocalDateTime? = null

    @Column(name = "END_DATE_TIME", nullable = false)
    open var endDateTime: LocalDateTime? = null

    @OneToMany(cascade = [CascadeType.PERSIST])
    @JoinColumn(name = "TRIP_ID")
    open var routes: MutableList<Route> = mutableListOf()

    fun getTotalTripDistanceInKm(): Int = routes.map { it.distanceInKm }.foldRight(0) { sum, next -> sum + next}
}