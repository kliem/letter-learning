package com.letter.learning.models

import javafx.collections.ListChangeListener.Change
import tornadofx.ViewModel
import tornadofx.asObservable
import tornadofx.listProperty
import tornadofx.onChange

class KnownLettersModel : ViewModel() {
    // listProperty() with no arguments returns an immutable list property.
    val letters = listProperty(mutableListOf<String>().asObservable())

    private val alphabetModel: AlphabetModel by inject()

    init {
        alphabetModel.letters.onChange { op: Change<*> ->
            op.next()
            letters.removeAll(op.removed.toSet())
        }
    }
}
