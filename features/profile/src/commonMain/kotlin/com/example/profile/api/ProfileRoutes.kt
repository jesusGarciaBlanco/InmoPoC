package com.example.profile.api

import androidx.navigation3.runtime.NavKey
import com.example.navigation.Navigator
import com.example.navigation.TopLevelNav
import com.example.resources.Res
import com.example.resources.face
import kotlinx.serialization.Serializable

@Serializable object ProfileRoute : NavKey, Navigator.RequiresLogin, TopLevelNav {
    override val icon = Res.drawable.face
}
@Serializable object ProfileDetailRoute : NavKey,Navigator.RequiresLogin
