package org.example.views

import tornadofx.View
import tornadofx.action
import tornadofx.borderpane
import tornadofx.button
import tornadofx.center
import tornadofx.fieldset
import tornadofx.form

class MainMenuView : View("Hello World") {
    override val root =
        borderpane {
            center {
                form {
                    fieldset {
                        button("Modify alphabet") {
                            action {
                                replaceWith(ModifyAlphabetView::class, sizeToScene = true)
                            }
                        }
                        button("Select known letters") {
                            action {
                                replaceWith(KnownLettersView::class, sizeToScene = true)
                            }
                        }
                        button("Known words") {
                            action {
                                replaceWith(KnownWordsView::class, sizeToScene = true)
                            }
                        }
                    }
                }
            }
        }
}
