package org.fevr.portfolio

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import org.fevr.portfolio.screens.AboutScreen
import org.fevr.portfolio.screens.ContactScreen
import org.fevr.portfolio.screens.MainScreen
import org.fevr.portfolio.screens.ProjectsScreen
import org.fevr.portfolio.visuals.CustomDarkColorScheme
import org.fevr.portfolio.visuals.CustomLightColorScheme
import org.fevr.portfolio.visuals.rubikFamily
import org.fevr.portfolio.visuals.rubikMono

@Composable
fun app() {
    val colorState = remember { mutableStateOf(true) }

    val colorScheme = if (colorState.value) CustomLightColorScheme else CustomDarkColorScheme

    val currentScreen = remember { mutableStateOf("Main") }

    MaterialTheme(colorScheme) {
        Scaffold(
            topBar = { topWebAppBar(colorState, currentScreen) }
        ) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center,
                content = {
                    when (currentScreen.value) {
                        "Main" -> MainScreen()
                        "About" -> AboutScreen()
                        "Contact me" -> ContactScreen()
                        "Experience" -> TODO()
                        "Projects" -> ProjectsScreen()
                        else -> MainScreen()
                    }
                }
            )
        }
    }
}

@Composable
fun topWebAppBar(state: MutableState<Boolean>, currentScreen: MutableState<String>) {
    TopAppBar(
        title = {
            Text(
                "Fabian",
                fontFamily = rubikMono(),
                modifier = Modifier.clickable { currentScreen.value = "Main" })
        },
        actions = {
            topActions(state, currentScreen)
        },
        elevation = 8.dp
    )
}

@Composable
fun topActions(state: MutableState<Boolean>, currentScreen: MutableState<String>) {
    val topList = listOf(
        "About",
        "Projects",
        "Experience",
        "Contact me"
    )
    topList.forEach {
        Text(
            it,
            modifier = Modifier.padding(10.dp).clickable(onClick = { currentScreen.value = it }),
            fontFamily = rubikFamily(),
            fontWeight = FontWeight.Bold
        )
    }

    Spacer(modifier = Modifier.padding(30.dp))
    Icon(Icons.Filled.Star, null, tint = Color.White)
    Switch(
        checked = state.value,
        onCheckedChange = { state.value = !state.value },
        colors = SwitchDefaults.colors(checkedThumbColor = Color.White, uncheckedThumbColor = Color.DarkGray)
    )
    Spacer(modifier = Modifier.padding(10.dp))
}