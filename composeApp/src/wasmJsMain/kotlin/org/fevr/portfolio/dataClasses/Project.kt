package org.fevr.portfolio.dataClasses

import org.jetbrains.compose.resources.DrawableResource

data class Project(val name:String, val description:String, val stack:List<String>, val image: DrawableResource, val uri:String)
