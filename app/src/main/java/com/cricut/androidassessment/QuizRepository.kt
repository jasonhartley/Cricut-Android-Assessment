package com.cricut.androidassessment

import com.cricut.androidassessment.model.MultipleChoiceQuestion
import com.cricut.androidassessment.model.MultipleSelectionQuestion
import com.cricut.androidassessment.model.OpenEndedQuestion
import com.cricut.androidassessment.model.QuizState
import com.cricut.androidassessment.model.TrueFalseQuestion
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

object QuizRepository {
    private val _quizState = MutableStateFlow(QuizState())
    val quiz: StateFlow<QuizState> = _quizState

    init {
        _quizState.value = QuizState(
            questions = listOf(
                TrueFalseQuestion(
                    question = "Jake Wharton is a programmer.",
                ),
                MultipleChoiceQuestion(
                    question = "What is the best programming language?",
                    choices = listOf(
                        "Java",
                        "Kotlin",
                        "Swift",
                    ),
                ),
                MultipleSelectionQuestion(
                    question = "What programming languages do you like?",
                    choices = listOf(
                        "Java",
                        "Kotlin",
                        "Swift",
                    ),
                ),
                OpenEndedQuestion(
                    question = "What language features in Kotlin do you like best?",
                ),
            ),
        )
    }
}