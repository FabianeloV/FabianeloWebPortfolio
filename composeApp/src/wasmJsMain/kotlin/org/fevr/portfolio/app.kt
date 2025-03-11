package org.fevr.portfolio

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import org.fevr.portfolio.screens.mainScreen
import org.fevr.portfolio.visuals.CustomDarkColorScheme
import org.fevr.portfolio.visuals.CustomLightColorScheme
import org.fevr.portfolio.visuals.rubikFamily
import org.fevr.portfolio.visuals.rubikMono

@Composable
fun app() {
    val colorState = remember { mutableStateOf(true) }

    val colorScheme = if (colorState.value) CustomLightColorScheme else CustomDarkColorScheme

    val scrollState = rememberLazyListState()

    MaterialTheme(colorScheme) {
        Scaffold(
            topBar = { topWebAppBar(colorState, scrollState) }
        ) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center,
                content = {
                    mainScreen(scrollState)
                }
            )
        }
    }
}

@Composable
fun topWebAppBar(state: MutableState<Boolean>, scrollState: LazyListState) {
    TopAppBar(
        title = {
            Text(
                "Fabian",
                fontFamily = rubikMono(),
                )
        },
        actions = {
            topActions(state, scrollState)
        },
        elevation = 8.dp
    )
}

@Composable
fun topActions(state: MutableState<Boolean>, scrollState: LazyListState) {
    val scope = rememberCoroutineScope()

    val topList = listOf(
        Pair("About",0),
        Pair("Experience",1),
        Pair("Projects",2),
        Pair("Contact me",3)
    )
    topList.forEach {
        Text(
            it.first,
            modifier = Modifier.padding(10.dp).clickable(onClick = { scope.launch { scrollState.animateScrollToItem(it.second) } }),
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