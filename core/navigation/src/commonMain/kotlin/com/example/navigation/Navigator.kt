package com.example.navigation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.navigation3.runtime.NavKey
import kotlin.text.get


/**
 * Handles navigation events (forward and back) by updating the navigation state.
 */
class Navigator(
    val startDestination: NavKey,
    private val loginRoute: NavKey
) {
    /**
     * Interfaz marcadora para rutas que requieren que el usuario esté autenticado.
     * Cualquier NavKey puede implementarla.
     */
    interface RequiresLogin

    private var onLoginSuccessRoute: NavKey? = null

    // Estado de autenticación, observable por Compose.
    var isLoggedIn by mutableStateOf(false)
        private set

    // La pila de navegación, observable por Compose.
    val backStack: SnapshotStateList<NavKey> = mutableStateListOf(startDestination)

    init {
        if (startDestination is RequiresLogin && !isLoggedIn) {
            onLoginSuccessRoute = startDestination
            backStack.add(loginRoute)
        } else {
            backStack.add(startDestination)
        }
    }
    /**
     * Navega a un nuevo destino.
     * Si el destino requiere login y el usuario no está autenticado,
     * guarda el destino y redirige a la pantalla de login.
     */
    fun goTo(destination: NavKey) {
        // Redirect to Login if it's necessary
        if (destination is RequiresLogin && !isLoggedIn) {
            onLoginSuccessRoute = destination
            backStack.add(loginRoute)
            return
        }

        // Si el usuario navega explícitamente a login, no hay redirección posterior.
        if (destination == loginRoute) {
            onLoginSuccessRoute = null
        }

        backStack.add(destination)
    }

    /**
     * Vuelve a la pantalla anterior en la pila.
     */
    fun goBack() {
        backStack.removeLastOrNull()
    }

    /**
     * Marca al usuario como autenticado y navega al destino guardado si existe.
     */
    fun login() {
        isLoggedIn = true

        onLoginSuccessRoute?.let { destination ->
            backStack.remove(loginRoute)
            backStack.add(destination)
            onLoginSuccessRoute = null
        }
    }

    /**
     * Cierra la sesión del usuario y elimina todas las pantallas protegidas de la pila.
     */
    fun logout() {
        isLoggedIn = false
        backStack.removeAll { it is RequiresLogin }
        if (backStack.isEmpty()) {
            backStack.add(loginRoute)
            onLoginSuccessRoute = startDestination
        }
    }
}

