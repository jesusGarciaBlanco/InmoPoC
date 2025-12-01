package com.example.register.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.navigation.Navigator
import com.example.registerapi.FinishRoute
import org.koin.compose.koinInject

@Composable
fun FinishScreen() {
    val navigator: Navigator = koinInject()
    Column(
    modifier = Modifier.fillMaxSize().background(Color.Yellow),
    horizontalAlignment = Alignment.CenterHorizontally,
    verticalArrangement = Arrangement.Center
    ) {
        Text("Finish Screen")

        Button(onClick = {
            navigator.goBack()
        }){
            Text("go back")
        }

        Button(onClick = {
            navigator.goBackTo(FinishRoute, inclusive = true)
        }){
            Text("close flow")
        }
    }
}
