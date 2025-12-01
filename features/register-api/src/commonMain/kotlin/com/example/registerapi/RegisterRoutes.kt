package com.example.registerapi

import com.example.navigation.Route
import kotlinx.serialization.Serializable


@Serializable object RegisterRoute : Route()

@Serializable object LegalNameRoute: Route()

@Serializable data class LegalAddressRoute(
    val legalName: String,
): Route()

@Serializable data class LegalResumeRoute(
    val legalName: String,
    val legalAddress: String,
): Route()

@Serializable object FinishRoute: Route()
