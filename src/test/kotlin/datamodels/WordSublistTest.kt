package datamodels

import com.letter.learning.datamodels.WordSublist
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

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
}
