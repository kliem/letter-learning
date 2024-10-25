package org.example.views

import javafx.scene.control.SelectionMode
import javafx.scene.control.TextInputDialog
import org.example.models.AlphabetModel
import tornadofx.View
import tornadofx.action
import tornadofx.borderpane
import tornadofx.button
import tornadofx.center
import tornadofx.fieldset
import tornadofx.form
import tornadofx.label
import tornadofx.listview
import tornadofx.onUserDelete

class ModifyAlphabetView : View("Modify alphabet") {
    private val alphabet: AlphabetModel by inject()

    override val root =
        borderpane {
            center {
                form {
                    fieldset {
                        label("Letters")
                        val listView =
                            listview(alphabet.letters) {
                                selectionModel.selectionMode = SelectionMode.SINGLE
                                onUserDelete {
                                    val selectedItem = selectionModel.selectedItem
                                    if (selectedItem != null) {
                                        alphabet.letters.remove(selectedItem)
                                    }
                                }
                            }
                        button("Add Letter") {
                            action {
                                val dialog = TextInputDialog("Enter new letter")
                                dialog.showAndWait().ifPresent {
                                    if (it !in alphabet.letters) {
                                        alphabet.letters.add(it)
                                    }
                                }
                            }
                        }
                        button("Remove Selected Letter") {
                            action {
                                val selectedItem = listView.selectionModel.selectedItem
                                if (selectedItem != null) {
                                    alphabet.letters.remove(selectedItem)
                                }
                            }
                        }
                        button("Confirm") {
                            action {
                                replaceWith(MainMenuView::class, sizeToScene = true)
                            }
                        }
                    }
                }
            }
        }
}
