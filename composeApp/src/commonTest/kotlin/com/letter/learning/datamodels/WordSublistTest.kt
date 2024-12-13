package com.letter.learning.datamodels

import io.kotest.core.spec.style.StringSpec
import io.kotest.data.Row2
import io.kotest.data.row
import io.kotest.datatest.withData
import io.kotest.matchers.shouldBe

class WordSublistTest :
    StringSpec({
        "sublist of empty list is empty" {
            // given
            val allWords = emptyList<String>()
            val sublist = WordSublist(allWords) { throw NotImplementedError() }

            // when
            val output = sublist.isEmpty()

            // then
            output shouldBe true
        }

        "an empty sublist" {
            // given
            val allWords = listOf("Word")
            val sublist = WordSublist(allWords) { false }

            // when
            val output = sublist.isEmpty()

            // then
            output shouldBe true
        }

        "a non-empty sublist" {
            // given
            val allWords = listOf("Word")
            val sublist = WordSublist(allWords) { true }

            // when
            val output = sublist.isEmpty()

            // then
            output shouldBe false
        }

        "iterate over the empty list" {
            // given
            val allWords = listOf("Word")
            val sublist = WordSublist(allWords) { false }

            // when
            val output = sublist.stream().toList()

            // then
            output shouldBe emptyList()
        }

        "iterate over a sublist" {
            // given
            val allWords = listOf("Word0", "Word1", "Word2", "Word3")
            val expectedSublist = listOf("Word1", "Word2")
            val sublist = WordSublist(allWords) { it in expectedSublist }

            // when
            val output = sublist.stream().toList()

            // then
            output shouldBe expectedSublist
        }

        withData<Row2<List<String>, List<String>>>(
            { "contains all returns true for sub-sublist" },
            subSublistAndSublist,
        ) { (subSublist, sublist) ->
            val foo = "contains all returns true for sub-sublist"
            // given
            val allWords = listOf("Word0", "Word1", "Word2", "Word3")
            val wordSublist = WordSublist(allWords) { it in sublist }

            // when
            val output = wordSublist.containsAll(subSublist)

            // then
            output shouldBe true
        }

        withData<Row2<List<String>, List<String>>>(
            { "contains returns true for each element of sub-sublist" },
            subSublistAndSublist,
        ) { (subSublist, sublist) ->
            // given
            val allWords = listOf("Word0", "Word1", "Word2", "Word3")
            val wordSublist = WordSublist(allWords) { it in sublist }

            // when
            val output = subSublist.all { wordSublist.contains(it) }

            // then
            output shouldBe true
        }

        withData<Row2<List<String>, List<String>>>(
            { "contains all returns false if non-sub-sublist" },
            notContainedSublist,
        ) { (otherList, sublist) ->
            // given
            val allWords = listOf("Word0", "Word1", "Word2", "Word3")
            val wordSublist = WordSublist(allWords) { it in sublist }

            // when
            val output = wordSublist.containsAll(otherList)

            // then
            output shouldBe false
        }

        withData<Row2<List<String>, List<String>>>(
            { "contains returns false for some element of non-sub-sublist" },
            notContainedSublist,
        ) { (otherList, sublist) ->
            // given
            val allWords = listOf("Word0", "Word1", "Word2", "Word3")
            val wordSublist = WordSublist(allWords) { it in sublist }

            // when
            val output = otherList.all { wordSublist.contains(it) }

            // then
            output shouldBe false
        }

        withData<Row2<List<List<String>>, List<String>>>(
            { "test intersections" },
            intersectionSamples,
        ) { (inputs, expected) ->
            // given
            val allWords = listOf("Word0", "Word1", "Word2", "Word3")
            val expectedSublist = WordSublist(allWords) { it in expected }
            val inputSublists = inputs.map { input -> WordSublist(allWords) { it in input } }

            // when
            val output = inputSublists.intersect()

            // then
            output shouldBe expectedSublist
        }
    })

val subSublistAndSublist =
    listOf(
        row(
            listOf("Word0"),
            listOf("Word0", "Word1"),
        ),
        row(
            listOf("Word1"),
            listOf("Word0", "Word1"),
        ),
        row(
            listOf("Word0", "Word1"),
            listOf("Word0", "Word1"),
        ),
        row(
            emptyList(),
            emptyList(),
        ),
    )

val notContainedSublist =
    listOf(
        row(
            listOf("Word2"),
            listOf("Word0", "Word1"),
        ),
        row(
            listOf("Word0", "Word3"),
            listOf("Word0", "Word1"),
        ),
        row(
            listOf("Word3"),
            emptyList<String>(),
        ),
    )

val intersectionSamples =
    listOf(
        row(
            listOf(
                listOf("Word0", "Word1"),
                listOf("Word0", "Word2"),
            ),
            listOf("Word0"),
        ),
        row(
            listOf(
                listOf("Word1"),
                emptyList(),
            ),
            emptyList(),
        ),
        row(
            listOf(
                listOf("Word0", "Word1"),
                listOf("Word2", "Word3"),
            ),
            emptyList(),
        ),
        row(
            listOf(
                listOf("Word0", "Word1"),
                listOf("Word0", "Word1"),
            ),
            listOf("Word0", "Word1"),
        ),
        row(
            listOf(listOf("Word0", "Word1")),
            listOf("Word0", "Word1"),
        ),
        row(
            listOf(
                listOf("Word0", "Word1"),
                listOf("Word2", "Word1"),
                listOf("Word3", "Word1", "Word2"),
            ),
            listOf("Word1"),
        ),
    )
