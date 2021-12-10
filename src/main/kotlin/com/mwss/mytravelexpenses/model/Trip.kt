package com.mwss.mytravelexpenses.model

import org.hibernate.Hibernate
import java.sql.Date
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZoneOffset
import java.util.*
import java.util.Date.*
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

    @ManyToOne
    @JoinColumn(name = "USER_02_ID")
    open var user02: User? = null

    fun getTotalTripDistanceInKm(): Double = routes.map { it.distanceInKm }.foldRight(0.0) { sum, next -> sum + next}

    fun getDescriptionString(): String {
        var dateString:String = "Unknown Date!!"

        val fromToString: String = if(routes.size == 0){
            "from -- to --"
        }else{
            "from ${routes[0].startLocation} to ${routes[routes.size - 1].endLocation}"
        }

        startDateTime?.let { start -> endDateTime?.let { end ->
            dateString = if(start.toLocalDate().equals(end.toLocalDate())){
                start.toLocalDate().toString()
            }else{
                "${start.toLocalDate().toString()} - ${end.toLocalDate().toString()}"
            }
        }
        }

        return "$dateString : $fromToString"
    }

}