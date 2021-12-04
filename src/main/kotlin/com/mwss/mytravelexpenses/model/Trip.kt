package com.mwss.mytravelexpenses.model

import org.hibernate.Hibernate
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

    @ManyToMany(cascade = [CascadeType.PERSIST])
    @JoinTable(name = "trip_routes",
        joinColumns = [JoinColumn(name = "trip_id")],
        inverseJoinColumns = [JoinColumn(name = "routes_id")])
    open var routes: MutableList<Route> = mutableListOf()

    fun getTotalTripDistanceInKm(): Double = routes.map { it.distanceInKm }.foldRight(0.0) { sum, next -> sum + next}

}