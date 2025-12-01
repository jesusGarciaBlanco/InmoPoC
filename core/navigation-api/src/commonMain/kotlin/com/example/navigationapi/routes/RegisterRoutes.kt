package com.example.navigationapi.routes

import kotlinx.serialization.Serializable


object RegisterRoutes {
    @Serializable
    data object RegisterHome : Route

    @Serializable
    data object LegalName : Route

    @Serializable
    data class LegalAddress(
        val legalName: String,
    ) : Route

    @Serializable
    data class LegalResume(
        val legalName: String,
        val legalAddress: String,
    ) : Route

    @Serializable
    object Finish : Route

}
