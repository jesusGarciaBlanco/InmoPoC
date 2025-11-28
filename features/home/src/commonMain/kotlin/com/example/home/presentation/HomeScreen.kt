package com.example.home.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.home.api.HomeDetailRoute
import com.example.navigation.Navigator
import org.koin.compose.koinInject

@Composable
fun HomeScreen(
) {
    val navigator: Navigator = koinInject()
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Red)
            .safeDrawingPadding()
            .clip(RoundedCornerShape(48.dp))
    ) {
        Text("Home Screen")
        Button(onClick = {
            navigator.goTo(HomeDetailRoute)
        }) {
            Text("Go to Home Detail")
        }
    }
}
