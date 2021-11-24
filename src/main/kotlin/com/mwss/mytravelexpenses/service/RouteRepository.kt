package com.mwss.mytravelexpenses.service;

import com.mwss.mytravelexpenses.model.Route
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.CrudRepository

interface RouteRepository : CrudRepository<Route, Long> {
}