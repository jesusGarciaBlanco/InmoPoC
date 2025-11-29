package com.example.navigation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.runtime.toMutableStateList


/**
 * Handles navigation events (forward and back) by updating the navigation state.
 */
class Navigator(
    private val state: NavigationState,
    private val loginRoute: Route,
) {
    // The route to which the user will be redirected after a successful login.
    private var onLoginSuccessRoute: Route? = null

    // The login state can be here or in a user repository.
    var isLoggedIn by mutableStateOf(false)
        private set

    // Exposes the combined navigation stack for the UI to observe.
    val combinedBackStack: SnapshotStateList<Route>
        get() = if (isLoggedIn) {
            val stacksInUse = if (state.currentTopLevelRoute == state.startRoute) {
                listOf(state.startRoute)
            } else {
                listOf(state.startRoute, state.currentTopLevelRoute)
            }
            stacksInUse.flatMap { state.backStacks[it] ?: emptyList() }.toMutableStateList()
        } else {
            state.backStacks[loginRoute]?.toMutableStateList() ?: mutableStateListOf(loginRoute)
        }

    init {
        if (state.backStacks[loginRoute] == null) {
            state.backStacks[loginRoute] = mutableStateListOf(loginRoute)
        }
        // Initial logic to force login if necessary.
        handleInitialLoginCheck()
    }

    private fun handleInitialLoginCheck() {
        if (state.startRoute.requiresLogin && !isLoggedIn) {
            onLoginSuccessRoute = state.startRoute
        }
    }

    /**
     * Navigates to a destination. This is the main method that should be called.
     */
    fun navigateTo(destination: Route) {
        if (destination.requiresLogin && !isLoggedIn) {
            onLoginSuccessRoute = destination
            // We don't need to do anything else; `combinedBackStack` will already return only the loginRoute.
            return
        }

        if (destination == loginRoute) {
            onLoginSuccessRoute = null
        }

        // --- Stack Navigation Logic ---
        if (destination in state.topLevelRoutes) {
            // It's a top-level route (a tab), we just switch to it.
            state.currentTopLevelRoute = destination
        } else {
            // It's a detail route, we add it to the active tab's stack.
            val stackToModify = if (isLoggedIn) state.currentTopLevelRoute else loginRoute
            state.backStacks[stackToModify]?.add(destination)
        }
    }

    /**
     * Navigates back in the current stack.
     */
    fun goBack() {
        // Determine the active stack based on the login state.
        val activeStackKey = if (isLoggedIn) state.currentTopLevelRoute else loginRoute
        val currentStack = state.backStacks[activeStackKey]
            ?: error("Navigation stack not found for $activeStackKey")

        // Back navigation logic for a LOGGED-IN user.
        if (isLoggedIn) {
            // If the current stack has only 1 element (the top-level route itself),
            // and we are NOT on the start route, we go back to the start route.
            if (currentStack.size <= 1 && state.currentTopLevelRoute != state.startRoute) {
                state.currentTopLevelRoute = state.startRoute
            } else if (currentStack.size > 1) {
                // If the stack has more than one element, we just remove the last one.
                currentStack.removeAt(currentStack.lastIndex)
            }
            // If we are on the start stack with 1 element, we do nothing (the user will exit the app).
        }
        // 3. Back navigation logic for a NOT-LOGGED-IN user.
        else {
            // If the stack (which will be the login stack) has more than one element (e.g., Login -> Register),
            // we just remove the last one to go back to the previous one.
            if (currentStack.size > 1) {
                currentStack.removeAt(currentStack.lastIndex)
            }
            // If only one element is left (Login), we do nothing to prevent the user from exiting the app.
        }
    }

    /**
     * Marks the user as logged in and handles redirection.
     */
    fun login() {
        isLoggedIn = true
        onLoginSuccessRoute?.let {
            navigateTo(it)
            onLoginSuccessRoute = null
        }
    }

    /**
     * Logs out the user and clears the state.
     */
    fun logout() {
        isLoggedIn = false
        // We reset the stacks to their initial state.
        state.topLevelRoutes.forEach { route ->
            // Instead of casting, we create a new SnapshotStateList.
            state.backStacks[route] = mutableListOf(route).toMutableStateList()
        }
        // We return to the start tab.
        state.currentTopLevelRoute = state.startRoute
        // We check if the start route requires login to redirect if necessary.
        handleInitialLoginCheck()
    }
}
