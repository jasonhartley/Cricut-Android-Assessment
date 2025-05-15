package com.cricut.androidassessment

import com.cricut.androidassessment.model.MultipleChoiceAnswer
import com.cricut.androidassessment.model.MultipleSelectionAnswer
import com.cricut.androidassessment.model.OpenEndedAnswer
import com.cricut.androidassessment.model.TrueFalseAnswer
import junit.framework.TestCase.assertFalse
import junit.framework.TestCase.assertTrue
import org.junit.Test

class AnswerTests {
    @Test
    fun `TrueFalseAnswer default is null`() {
        val trueFalseAnswer = TrueFalseAnswer.DEFAULT
        assertTrue(trueFalseAnswer.answer == null)
    }

    @Test
    fun `TrueFalseAnswer default is not answered`() {
        val trueFalseAnswer = TrueFalseAnswer.DEFAULT
        assertFalse(trueFalseAnswer.isAnswered)
    }

    @Test
    fun `TrueFalseAnswer null is not answered`() {
        val trueFalseAnswer = TrueFalseAnswer(null)
        assertFalse(trueFalseAnswer.isAnswered)
    }

    @Test
    fun `TrueFalseAnswer true is answered`() {
        val trueFalseAnswer = TrueFalseAnswer(true)
        assert(trueFalseAnswer.isAnswered)
    }

    @Test
    fun `TrueFalseAnswer false is answered`() {
        val trueFalseAnswer = TrueFalseAnswer(false)
        assert(trueFalseAnswer.isAnswered)
    }

    @Test
    fun `MultipleChoiceAnswer default is empty String`() {
        val multipleChoiceAnswer = MultipleChoiceAnswer.DEFAULT
        assertTrue(multipleChoiceAnswer.answer.isEmpty())
    }

    @Test
    fun     `MultipleChoiceAnswer default is not answered`() {
        val multipleChoiceAnswer = MultipleChoiceAnswer.DEFAULT
        assertFalse(multipleChoiceAnswer.isAnswered)
    }

    @Test
    fun `MultipleChoiceAnswer empty or blank is not answered`() {
        val multipleChoiceAnswer = MultipleChoiceAnswer("  ")
        assertFalse(multipleChoiceAnswer.isAnswered)
    }

    @Test
    fun `MultipleChoiceAnswer non-empty is answered`() {
        val multipleChoiceAnswer = MultipleChoiceAnswer("foo")
        assertTrue(multipleChoiceAnswer.isAnswered)
    }

    @Test
    fun `MultipleSelectionAnswer default is empty list`() {
        val multipleSelectionAnswer = MultipleSelectionAnswer.DEFAULT
        assertTrue(multipleSelectionAnswer.answer.isEmpty())
    }

    @Test
    fun `MultipleSelectionAnswer default is not answered`() {
        val multipleSelectionAnswer = MultipleSelectionAnswer.DEFAULT
        assertFalse(multipleSelectionAnswer.isAnswered)
    }

    @Test
    fun `MultipleSelectionAnswer empty list is not answered`() {
        val multipleSelectionAnswer = MultipleSelectionAnswer(emptyList())
        assertFalse(multipleSelectionAnswer.isAnswered)
    }

    @Test
    fun `MultipleSelectionAnswer non-empty list with only empty or blank entries is unanswered`() {
        val multipleSelectionAnswer = MultipleSelectionAnswer(listOf("", "     "))
        assertFalse(multipleSelectionAnswer.isAnswered)
    }

    @Test
    fun `MultipleSelectionAnswer non-empty list with at least one non-empty, non-blank entry is answered`() {
        val multipleSelectionAnswer = MultipleSelectionAnswer(listOf("", " ", "foo"))
        assertTrue(multipleSelectionAnswer.isAnswered)
    }

    @Test
    fun `MultipleSelectionAnswer `() {
        val multipleSelectionAnswer = MultipleSelectionAnswer.DEFAULT
        assertFalse(multipleSelectionAnswer.isAnswered)
    }

    @Test
    fun `OpenEndedAnswer default is empty String`() {
        val openEndedAnswer = OpenEndedAnswer.DEFAULT
        assertTrue(openEndedAnswer.answer.isEmpty())
    }

    @Test
    fun `OpenEndedAnswer default is not answered`() {
        val openEndedAnswer = OpenEndedAnswer.DEFAULT
        assertFalse(openEndedAnswer.isAnswered)
    }

    @Test
    fun `OpenEndedAnswer empty or blank is not answered`() {
        val openEndedAnswer = OpenEndedAnswer(" ")
        assertFalse(openEndedAnswer.isAnswered)
    }

    @Test
    fun `OpenEndedAnswer non-empty is answered`() {
        val openEndedAnswer = OpenEndedAnswer("foo")
        assertTrue(openEndedAnswer.isAnswered)
    }

}