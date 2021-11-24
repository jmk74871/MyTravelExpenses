package com.mwss.mytravelexpenses.service;

import com.mwss.mytravelexpenses.model.Trip
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.CrudRepository

interface TripRepository : CrudRepository<Trip, Long> {
}