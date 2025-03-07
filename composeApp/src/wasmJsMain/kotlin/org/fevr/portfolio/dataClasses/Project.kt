package org.fevr.portfolio.dataClasses

import androidx.compose.ui.graphics.vector.ImageVector
import org.jetbrains.compose.resources.DrawableResource

data class Project(val name:String, val description:String, val stack:List<Pair<ImageVector, String>>, val image: DrawableResource)
