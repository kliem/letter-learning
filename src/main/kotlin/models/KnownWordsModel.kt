package org.example.models

import javafx.collections.ListChangeListener.Change
import org.example.datamodels.intersect
import tornadofx.ViewModel
import tornadofx.asObservable
import tornadofx.listProperty
import tornadofx.onChange
import tornadofx.stringProperty

class KnownWordsModel : ViewModel() {
    // listProperty() with no arguments returns an immutable list property.
    val knownWords = listProperty(mutableListOf<String>().asObservable())
    val numberOfKnownWords = stringProperty("")

    private val wordsWithoutLetter: WordsWithoutLetter by inject()
    private val knownLetters: KnownLettersModel by inject()

    init {
        updateList()
        wordsWithoutLetter.wordsWithoutLetterMap.onChange {
            updateList()
        }
        knownLetters.letters.onChange { _: Change<*> ->
            updateList()
        }
    }

    private fun updateList() {
        knownWords.clear()
        wordsWithoutLetter.wordsWithoutLetterMap
            .filterKeys { it !in knownLetters.letters }
            .values
            .intersect()
            .forEach {
                knownWords.add(it)
            }
        numberOfKnownWords.set("${knownWords.size} total")
    }
}
