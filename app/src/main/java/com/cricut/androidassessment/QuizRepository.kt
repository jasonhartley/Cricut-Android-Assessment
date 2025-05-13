package com.cricut.androidassessment

import com.cricut.androidassessment.model.MultipleChoiceQuestion
import com.cricut.androidassessment.model.MultipleSelectionQuestion
import com.cricut.androidassessment.model.OpenEndedQuestion
import com.cricut.androidassessment.model.QuizState
import com.cricut.androidassessment.model.TrueFalseQuestion
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class QuizRepository @Inject constructor() {
    private val _quizState = MutableStateFlow(QuizState())
    val quiz: StateFlow<QuizState> = _quizState

    init {
        populate()
    }

    private fun populate() {
        _quizState.value = QuizState(
            questions = listOf(
                TrueFalseQuestion(
                    questionText = "Jake Wharton is a programmer.",
                ),
                MultipleChoiceQuestion(
                    questionText = "What is the best programming language?",
                    choices = listOf(
                        "Java",
                        "Kotlin",
                        "Swift",
                        "Objective-C",
                    ),
                ),
                MultipleSelectionQuestion(
                    questionText = "What programming languages do you like?",
                    choices = listOf(
                        "Java",
                        "Kotlin",
                        "Swift",
                        "Objective-C",
                    ),
                ),
                OpenEndedQuestion(
                    questionText = "What language features in Kotlin do you like best?",
                ),
            ),
        )
    }
}