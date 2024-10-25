package com.letter.learning

import com.letter.learning.views.MainMenuView
import tornadofx.App
import tornadofx.launch

class LetterLearningApp : App(MainMenuView::class)

fun main(args: Array<String>) {
    launch<LetterLearningApp>()
}
