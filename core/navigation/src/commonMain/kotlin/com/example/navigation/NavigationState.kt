package com.example.navigation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.runtime.toMutableStateList
import com.example.navigationapi.routes.Route

class NavigationState(
    val startRoute: Route,
    val topLevelRoutes: Set<Route>
) {
    val backStacks: MutableMap<Route, SnapshotStateList<Route>> =
        topLevelRoutes.associateWith { mutableListOf(it).toMutableStateList() }
            .toMutableMap()

    var currentTopLevelRoute: Route by mutableStateOf(startRoute)
}
