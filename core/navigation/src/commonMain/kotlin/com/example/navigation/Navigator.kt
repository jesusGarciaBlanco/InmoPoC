package com.example.navigation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.runtime.toMutableStateList
import com.example.navigationapi.routes.Route
import com.example.navigationapi.marker.RequiresLogin

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
        if (state.startRoute is RequiresLogin && !isLoggedIn) {
            onLoginSuccessRoute = state.startRoute
        }
    }

    /**
     * Navigates to a destination. This is the main method that should be called.
     */
    fun navigateTo(destination: Route) {
        if (destination is RequiresLogin && !isLoggedIn) {
            onLoginSuccessRoute = destination
            // We don't need to do anything else; `combinedBackStack` will already return only the loginRoute.
            return
        }

        // Si el usuario navega explícitamente a login, no hay redirección posterior.
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
     * Navigates to a destination, clearing the back stack up to a specific route beforehand.
     *
     * @param destination The new route to navigate to.
     * @param popupTo The route to pop the stack up to.
     * @param inclusive If true, the `popupTo` route itself will also be removed from the stack.
     */
    fun navigateTo(destination: Route, popupTo: Route, inclusive: Boolean = false) {
        // First, handle the pop operation.
        // Determine which stack we need to modify.
        val activeStackKey = if (isLoggedIn) state.currentTopLevelRoute else loginRoute
        val currentStack = state.backStacks[activeStackKey]
            ?: error("Navigation stack not found for $activeStackKey")

        // Find the index of the `popupTo` route in the current stack.
        val popupToIndex = currentStack.indexOf(popupTo)

        if (popupToIndex != -1) {
            // Calculate the index of the last element to keep.
            // If inclusive is true, we pop up to and including the element, so we keep elements up to the one *before* it.
            // If inclusive is false, we keep the `popupTo` element itself.
            val lastIndexToKeep = if (inclusive) popupToIndex - 1 else popupToIndex

            // If lastIndexToKeep is valid, remove all elements after it.
            if (lastIndexToKeep < currentStack.size - 1) {
                // We remove from the end backwards to avoid index shifting issues.
                // The range is from the last element down to the one just after `lastIndexToKeep`.
                for (i in currentStack.lastIndex downTo lastIndexToKeep + 1) {
                    currentStack.removeAt(i)
                }
            }
        }

        // After popping, perform the regular navigation to the destination.
        navigateTo(destination)
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
     * Goes back in the stack to a specific destination, clearing all routes in between.
     *
     * @param destination The route to go back to.
     * @param inclusive If true, the `destination` route itself will also be removed, going back to the screen before it.
     */
    fun goBackTo(destination: Route, inclusive: Boolean = false) {
        // Determine which stack we are operating on.
        val activeStackKey = if (isLoggedIn) state.currentTopLevelRoute else loginRoute
        val currentStack = state.backStacks[activeStackKey]
            ?: error("Navigation stack not found for $activeStackKey")

        // Find the index of the destination route in the current stack.
        val destinationIndex = currentStack.indexOf(destination)

        // If the destination is not found, we can't do anything.
        if (destinationIndex == -1) {
            // Optional: Log a warning or error.
            // For now, we do nothing.
            return
        }

        // Calculate the target index to pop down to.
        // If inclusive, we want to remove the destination as well, so the target is the one *before* it.
        // If not inclusive, the destination is the last element we want to see, so we don't remove it.
        val targetIndex = if (inclusive) destinationIndex -1 else destinationIndex

        // We only proceed if there are elements to remove.
        if (targetIndex < currentStack.size - 1) {
            // Remove all elements from the end of the list down to the one after our target.
            for (i in currentStack.lastIndex downTo targetIndex + 1) {
                currentStack.removeAt(i)
            }
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
