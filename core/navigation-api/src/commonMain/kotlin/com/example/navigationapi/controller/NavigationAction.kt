package com.example.navigationapi.controller

import com.example.navigationapi.routes.Route


/**
 * Represents a specific navigation command to be executed by the Navigator.
 * This allows events to describe not just *where* to go, but *how* to get there.
 */
sealed interface NavigationAction {
    /**
     * Standard navigation to a destination.
     */
    data class NavigateTo(val route: Route) : NavigationAction

    /**
     * Navigates to a destination, clearing the back stack up to a specific route.
     */
    data class NavigateToWithPopUp(
        val route: Route,
        val popUpTo: Route,
        val inclusive: Boolean = false,
    ) : NavigationAction

    /**
     * Goes back to a specific destination in the stack, clearing all routes in between.
     */
    data class GoBackTo(
        val route: Route,
        val inclusive: Boolean = false,
    ) : NavigationAction

    /**
     * Standard back navigation.
     */
    data object GoBack : NavigationAction

    /**
     * Logs the user in.
     */
    data object Login : NavigationAction

    /**
     * Logs the user out.
     */
    data object Logout : NavigationAction
}
