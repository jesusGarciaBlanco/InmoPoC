package com.example.navigationapi.event

import com.example.navigationapi.controller.NavigationAction
import com.example.navigationapi.routes.Route
import com.example.navigationapi.marker.TopLevelNav
import com.example.navigationapi.routes.HomeRoutes


object HomeEvent {
    data object OnHomeDetailClick : Event {
        override fun toAction(): NavigationAction = NavigationAction.NavigateTo(HomeRoutes.Detail)
    }
    data class OnBottomNavItemClick(val topLevelRoute: TopLevelNav) : Event{
        override fun toAction(): NavigationAction = NavigationAction.NavigateTo(topLevelRoute as Route)
    }
}

