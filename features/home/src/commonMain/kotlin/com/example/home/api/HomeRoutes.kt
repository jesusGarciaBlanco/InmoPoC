package com.example.home.api


import androidx.navigation3.runtime.NavKey
import com.example.navigation.Navigator
import com.example.navigation.TopLevelNav
import com.example.resources.Res
import com.example.resources.home
import kotlinx.serialization.Serializable

@Serializable object HomeRoute : NavKey, Navigator.RequiresLogin, TopLevelNav {
    override val icon = Res.drawable.home
}
@Serializable object HomeDetailRoute : NavKey, Navigator.RequiresLogin
