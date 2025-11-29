package com.example.navigation

import androidx.navigation3.runtime.NavKey
import org.jetbrains.compose.resources.DrawableResource


abstract class Route : NavKey {
    open val isTopLevel: Boolean = false
    open val requiresLogin: Boolean = false

    open val icon: DrawableResource? = null
}
