package com.example.controller

import com.example.coroutines.AppCoroutineScope
import com.example.navigationapi.controller.NavEventController
import com.example.navigationapi.controller.NavigationAction
import com.example.navigationapi.routes.Route
import com.example.navigationapi.event.Event
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.shareIn


internal class NavEventControllerImpl(
    private val appCoroutineScope: AppCoroutineScope,
) : NavEventController {

    private val navigationEventState: MutableSharedFlow<Event> = MutableSharedFlow()

    override val routeState: SharedFlow<NavigationAction> =
        navigationEventState
            .map { event ->
                event.toAction()
            }.shareIn(
                scope = CoroutineScope(appCoroutineScope.context),
                started = SharingStarted.WhileSubscribed(),
            )

    override fun sendEvent(event: Event) {
        appCoroutineScope.launch {
            navigationEventState.emit(event)
        }
    }
}
