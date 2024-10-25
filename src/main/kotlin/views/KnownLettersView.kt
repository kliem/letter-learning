package com.letter.learning.views

import javafx.scene.control.ListView
import javafx.scene.control.SelectionMode
import com.letter.learning.models.AlphabetModel
import com.letter.learning.models.KnownLettersModel
import tornadofx.View
import tornadofx.action
import tornadofx.borderpane
import tornadofx.button
import tornadofx.center
import tornadofx.fieldset
import tornadofx.form
import tornadofx.label
import tornadofx.listview
import tornadofx.onUserSelect

class KnownLettersView : View("Select known letters") {
    private val knownLetters: KnownLettersModel by inject()
    private val alphabet: AlphabetModel by inject()

    private lateinit var listView: ListView<String>

    override val root =
        borderpane {
            center {
                form {
                    fieldset {
                        label("Known letters")
                        listView =
                            listview(alphabet.letters) {
                                selectionModel.selectionMode = SelectionMode.MULTIPLE
                                onUserSelect {
                                    updateLettersAndLeave()
                                }
                            }
                        button("Confirm") {
                            action {
                                updateLettersAndLeave()
                            }
                        }
                    }
                }
            }
        }

    private fun updateLettersAndLeave() {
        knownLetters.letters.removeAll(alphabet.letters)
        listView.selectionModel.selectedItems.forEach { knownLetters.letters.add(it) }
        replaceWith(MainMenuView::class, sizeToScene = true)
    }
}
