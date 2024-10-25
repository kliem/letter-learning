package org.example.views

import org.example.models.KnownWordsModel
import tornadofx.View
import tornadofx.action
import tornadofx.borderpane
import tornadofx.button
import tornadofx.center
import tornadofx.fieldset
import tornadofx.form
import tornadofx.label
import tornadofx.listview
import tornadofx.px
import tornadofx.style
import tornadofx.text

class KnownWordsView : View("View known words") {
    private val knownWords: KnownWordsModel by inject()

    override val root =
        borderpane {
            center {
                form {
                    style {
                        fontSize = 40.0.px
                    }
                    fieldset {
                        label("Known words")
                        listview(knownWords.knownWords)
                        button("Back") {
                            action {
                                replaceWith(MainMenuView::class, sizeToScene = true)
                            }
                        }
                        text(knownWords.numberOfKnownWords)
                    }
                }
            }
        }
}
