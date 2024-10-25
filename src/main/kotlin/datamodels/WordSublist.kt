package com.letter.learning.datamodels

import java.util.BitSet

data class WordSublist(
    private val allWords: List<String>,
    private val indices: BitSet,
) : Set<String> {
    constructor(allWords: List<String>, isContained: (String) -> Boolean) : this(
        allWords,
        BitSet(allWords.size).apply {
            allWords.forEachIndexed { index, word ->
                if (isContained(word)) this.set(index)
            }
        },
    )

    override val size: Int
        get() = indices.cardinality()

    override fun isEmpty(): Boolean = indices.isEmpty

    override fun iterator(): Iterator<String> {
        return object : Iterator<String> {
            private var index = -1

            override fun hasNext(): Boolean = indices.nextSetBit(index + 1) != -1

            override fun next(): String {
                index = indices.nextSetBit(index + 1)
                return allWords[index]
            }
        }
    }

    override fun containsAll(elements: Collection<String>): Boolean = elements.all { this.contains(it) }

    override fun contains(element: String): Boolean {
        val index = allWords.indexOf(element)
        if (index != -1) {
            return indices[index]
        }
        return false
    }

    fun intersect(other: WordSublist) =
        WordSublist(
            allWords,
            (indices.clone() as BitSet).apply { this.and(other.indices) },
        )
}

fun Collection<WordSublist>.intersect(): WordSublist =
    this.reduce { a, b ->
        a.intersect(b)
    }
