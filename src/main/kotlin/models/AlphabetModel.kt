package com.letter.learning.models

import tornadofx.ViewModel
import tornadofx.listProperty

class AlphabetModel : ViewModel() {
    val letters =
        listProperty(
            "a",
            "b",
            "c",
            "d",
            "e",
            "f",
            "g",
            "h",
            "i",
            "j",
            "k",
            "l",
            "m",
            "n",
            "o",
            "p",
            "q",
            "r",
            "s",
            "t",
            "u",
            "v",
            "w",
            "x",
            "y",
            "z",
            "ei",
            "au",
            "eu",
            "ä",
            "ö",
            "ü",
            "ß",
            "ch",
            "sch",
            "sp",
            "st",
            "ie",
        )
}
