package com.example.navigationapi.controller

import com.example.navigationapi.routes.Route
import com.example.navigationapi.event.Event
import kotlinx.coroutines.flow.SharedFlow

/**
 * Controller responsible to handle the navigation events.
 */
interface NavEventController {

    /**
     * Flow to observe the destination changes.
     */
    val routeState: SharedFlow<NavigationAction>

    /**
     * Sends the event to the controller.
     *
     * @param event the event to be sent
     */
    fun sendEvent(event: Event)
}
