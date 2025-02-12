package org.fevr.portfolio.visuals

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import fabianelowebportfolio.composeapp.generated.resources.*
import fabianelowebportfolio.composeapp.generated.resources.Res
import fabianelowebportfolio.composeapp.generated.resources.Rubik_Bold
import fabianelowebportfolio.composeapp.generated.resources.Rubik_Regular
import org.jetbrains.compose.resources.Font

@Composable
fun rubikFamily() = FontFamily(
    Font(Res.font.Rubik_Regular, weight = FontWeight.Normal),
    Font(Res.font.Rubik_Bold, weight = FontWeight.Bold),
    Font(Res.font.Rubik_Black, weight = FontWeight.Black)
)

@Composable
fun rubikMono() = FontFamily(Font(Res.font.RubikMonoOne_Regular))