package com.letter.learning.models

import com.fasterxml.jackson.databind.ObjectMapper
import javafx.beans.property.ListProperty
import tornadofx.ViewModel
import tornadofx.asObservable
import tornadofx.listProperty

class AllWordsModel : ViewModel() {
    val words: ListProperty<String>

    init {
        val mapper = ObjectMapper()
        val json = mapper.readTree(this::class.java.getResource("/etymwb-headwords.json"))
        val wordList = mutableListOf<String>()
        json.fields().forEach { (key, _) ->
            wordList.add(key)
        }
        words = listProperty(wordList.asObservable())
    }
}
