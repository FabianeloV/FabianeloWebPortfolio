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
import org.fevr.portfolio.TopActions.*
import org.fevr.portfolio.visuals.CustomDarkColorScheme
import org.fevr.portfolio.visuals.CustomLightColorScheme
import org.fevr.portfolio.visuals.rubikMono

@Composable
fun app() {
    val colorState = remember { mutableStateOf(false) }

    val colorScheme = if (colorState.value) CustomLightColorScheme else CustomDarkColorScheme

    MaterialTheme(colorScheme) {
        Scaffold(
            topBar = { topWebAppBar(colorState) }
        ) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center,
                content = {
                    Column {
                        OutlinedTextField("", onValueChange = {}, label = { Text("Prueba") })
                        Button(onClick = { colorState.value = !colorState.value }) {
                            Text("Cambiar")
                        }
                    }

                }
            )
        }
    }
}

@Composable
fun topWebAppBar(state: MutableState<Boolean>) {
     val topList = listOf(
        About,
        Projects,
        Exp,
        Contact
    )
    TopAppBar(
        title = { Text("Fabian Verdesoto", fontFamily = rubikMono()) },
        actions = {
            topActions(state)
        },
        elevation = 8.dp
    )
}

@Composable
fun topActions(state: MutableState<Boolean>){
    val topList = listOf(
        About,
        Projects,
        Exp,
        Contact
    )
    topList.forEach {
        Text(it.text, modifier = Modifier.padding(10.dp).clickable(onClick = it.click), fontWeight = FontWeight.Bold)
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