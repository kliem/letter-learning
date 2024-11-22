package com.letter.learning.views

import tornadofx.View
import tornadofx.action
import tornadofx.borderpane
import tornadofx.button
import tornadofx.center
import tornadofx.fieldset
import tornadofx.form
import tornadofx.label

class WelcomeView : View("Welcome") {
    override val root =
        borderpane {
            center {
                form {
                    fieldset {
                        label("Copyright:") {
                            text = "Wortliste entnommen aus: Wolfgang Pfeifer et al., Etymologisches \n" +
                                "Wörterbuch des Deutschen (1993), zur Verfügung gestellt vom Digitalen \n" +
                                "Wörterbuch der deutschen Sprache, <https://www.dwds.de/d/api>, abgerufen \n" +
                                "am 30.10.2024."
                        }
                        button("Get Started") {
                            action {
                                replaceWith(MainMenuView::class, sizeToScene = true)
                            }
                        }
                    }
                }
            }
        }
}
