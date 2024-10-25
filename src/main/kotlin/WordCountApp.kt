package org.example

import org.example.views.MainMenuView
import tornadofx.App
import tornadofx.launch

class WordCountApp : App(MainMenuView::class)

fun main(args: Array<String>) {
    launch<WordCountApp>()
}
