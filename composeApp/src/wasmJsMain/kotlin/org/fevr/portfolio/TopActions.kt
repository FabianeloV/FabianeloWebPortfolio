package org.fevr.portfolio

sealed class TopActions(val text:String, val click:()->Unit) {
    data object About:TopActions(
        text = "About",
        click = {}
    )
    data object Projects:TopActions(
        text = "Projects",
        click = {}
    )
    data object Exp:TopActions(
        text = "Experience",
        click = {}
    )
    data object Contact:TopActions(
        text = "Contact me",
        click = {}
    )
}