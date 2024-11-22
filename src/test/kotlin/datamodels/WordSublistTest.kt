package datamodels

import com.letter.learning.datamodels.WordSublist
import com.letter.learning.datamodels.intersect
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

class WordSublistTest {
    @Test
    fun `sublist of empty list is empty`() {
        // given
        val allWords = emptyList<String>()
        val sublist = WordSublist(allWords) { throw NotImplementedError() }

        // when
        val output = sublist.isEmpty()

        // then
        Assertions.assertThat(output).isTrue()
    }

    @Test
    fun `an empty sublist`() {
        // given
        val allWords = listOf("Word")
        val sublist = WordSublist(allWords) { false }

        // when
        val output = sublist.isEmpty()

        // then
        Assertions.assertThat(output).isTrue()
    }

    @Test
    fun `a non-empty sublist`() {
        // given
        val allWords = listOf("Word")
        val sublist = WordSublist(allWords) { true }

        // when
        val output = sublist.isEmpty()

        // then
        Assertions.assertThat(output).isFalse()
    }

    @Test
    fun `iterate over the empty list`() {
        // given
        val allWords = listOf("Word")
        val sublist = WordSublist(allWords) { false }

        // when
        val output = sublist.stream().toList()

        // then
        Assertions.assertThat(output).isEmpty()
    }

    @Test
    fun `iterate over a sublist`() {
        // given
        val allWords = listOf("Word0", "Word1", "Word2", "Word3")
        val expectedSublist = listOf("Word1", "Word2")
        val sublist = WordSublist(allWords) { it in expectedSublist }

        // when
        val output = sublist.stream().toList()

        // then
        Assertions.assertThat(output).isEqualTo(expectedSublist)
    }

    @ParameterizedTest
    @MethodSource("subSublistAndSublist")
    fun `contains all returns true for sub-sublist`(
        subSubList: List<String>,
        sublist: List<String>,
    ) {
        // given
        val allWords = listOf("Word0", "Word1", "Word2", "Word3")
        val wordSublist = WordSublist(allWords) { it in sublist }

        // when
        val output = wordSublist.containsAll(subSubList)

        // then
        Assertions.assertThat(output).isTrue()
    }

    @ParameterizedTest
    @MethodSource("subSublistAndSublist")
    fun `contains returns true for each element of sub-sublist`(
        subSubList: List<String>,
        sublist: List<String>,
    ) {
        // given
        val allWords = listOf("Word0", "Word1", "Word2", "Word3")
        val wordSublist = WordSublist(allWords) { it in sublist }

        // when
        val output = subSubList.all { wordSublist.contains(it) }

        // then
        Assertions.assertThat(output).isTrue()
    }

    @ParameterizedTest
    @MethodSource("notContainedSublist")
    fun `contains all returns false if non-sub-sublist`(
        otherList: List<String>,
        sublist: List<String>,
    ) {
        // given
        val allWords = listOf("Word0", "Word1", "Word2", "Word3")
        val wordSublist = WordSublist(allWords) { it in sublist }

        // when
        val output = wordSublist.containsAll(otherList)

        // then
        Assertions.assertThat(output).isFalse()
    }

    @ParameterizedTest
    @MethodSource("notContainedSublist")
    fun `contains returns false for some element of non-sub-sublist`(
        otherList: List<String>,
        sublist: List<String>,
    ) {
        // given
        val allWords = listOf("Word0", "Word1", "Word2", "Word3")
        val wordSublist = WordSublist(allWords) { it in sublist }

        // when
        val output = otherList.all { wordSublist.contains(it) }

        // then
        Assertions.assertThat(output).isFalse()
    }

    @ParameterizedTest
    @MethodSource("intersectionSamples")
    fun `test intersection`(
        inputs: List<List<String>>,
        expected: List<String>,
    ) {
        // given
        val allWords = listOf("Word0", "Word1", "Word2", "Word3")
        val expectedSublist = WordSublist(allWords) { it in expected }
        val inputSublists = inputs.map { input -> WordSublist(allWords) { it in input } }

        // when
        val output = inputSublists.intersect()

        // then
        Assertions.assertThat(output).isEqualTo(expectedSublist)
    }

    companion object {
        @JvmStatic
        fun subSublistAndSublist() =
            listOf(
                Arguments.of(
                    listOf("Word0"),
                    listOf("Word0", "Word1"),
                ),
                Arguments.of(
                    listOf("Word1"),
                    listOf("Word0", "Word1"),
                ),
                Arguments.of(
                    listOf("Word0", "Word1"),
                    listOf("Word0", "Word1"),
                ),
                Arguments.of(
                    emptyList<String>(),
                    emptyList<String>(),
                ),
            )

        @JvmStatic
        fun notContainedSublist() =
            listOf(
                Arguments.of(
                    listOf("Word2"),
                    listOf("Word0", "Word1"),
                ),
                Arguments.of(
                    listOf("Word0", "Word3"),
                    listOf("Word0", "Word1"),
                ),
                Arguments.of(
                    listOf("Word3"),
                    emptyList<String>(),
                ),
            )

        @JvmStatic
        fun intersectionSamples() =
            listOf(
                Arguments.of(
                    listOf(
                        listOf("Word0", "Word1"),
                        listOf("Word0", "Word2"),
                    ),
                    listOf("Word0"),
                ),
                Arguments.of(
                    listOf(
                        listOf("Word1"),
                        emptyList(),
                    ),
                    emptyList<String>(),
                ),
                Arguments.of(
                    listOf(
                        listOf("Word0", "Word1"),
                        listOf("Word2", "Word3"),
                    ),
                    emptyList<String>(),
                ),
                Arguments.of(
                    listOf(
                        listOf("Word0", "Word1"),
                        listOf("Word0", "Word1"),
                    ),
                    listOf("Word0", "Word1"),
                ),
                Arguments.of(
                    listOf(listOf("Word0", "Word1")),
                    listOf("Word0", "Word1"),
                ),
                Arguments.of(
                    listOf(
                        listOf("Word0", "Word1"),
                        listOf("Word2", "Word1"),
                        listOf("Word3", "Word1", "Word2"),
                    ),
                    listOf("Word1"),
                ),
            )
    }
}
