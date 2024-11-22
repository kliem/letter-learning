package com.letter.learning

import com.letter.learning.views.WelcomeView
import tornadofx.App
import tornadofx.launch

class LetterLearningApp : App(WelcomeView::class)

fun main(args: Array<String>) {
    launch<LetterLearningApp>()
}
