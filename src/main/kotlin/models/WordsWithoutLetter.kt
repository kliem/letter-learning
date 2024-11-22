package com.letter.learning.models

import com.letter.learning.datamodels.WordSublist
import javafx.collections.ListChangeListener.Change
import tornadofx.ViewModel
import tornadofx.asObservable
import tornadofx.mapProperty
import tornadofx.onChange

class WordsWithoutLetter : ViewModel() {
    val wordsWithoutLetterMap = mapProperty(mutableMapOf<String, WordSublist>().asObservable())

    private val alphabetModel: AlphabetModel by inject()
    private val allWordsModel: AllWordsModel by inject()

    init {
        updateMapping()
        alphabetModel.letters.onChange { _: Change<*> ->
            updateMapping()
        }
        allWordsModel.words.onChange { _: Change<*> ->
            updateMapping()
        }
    }

    private fun updateMapping() {
        wordsWithoutLetterMap.clear()
        wordsWithoutLetterMap.putAll(
            alphabetModel.letters.associateWith {
                WordSublist(allWordsModel.words) { word ->
                    word.all { "$it".lowercase() in alphabetModel.letters } && !word.lowercase().contains(it)
                }
            },
        )
    }
}
