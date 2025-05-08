package com.cricut.androidassessment.model

import com.cricut.androidassessment.model.QuestionType.*

interface Question {
    val type: QuestionType
    val question: String
}


data class TrueFalseQuestion(
    override val type: QuestionType = TRUE_FALSE,
    override val question: String,
) : Question


data class MultipleChoiceQuestion(
    override val type: QuestionType = MULTIPLE_CHOICE,
    override val question: String,
    val choices: List<String>,
) : Question


data class MultipleSelectionQuestion(
    override val type: QuestionType = MULTIPLE_SELECTION,
    override val question: String,
    val choices: List<String>,
) : Question


data class OpenEndedQuestion(
    override val type: QuestionType = OPEN_ENDED,
    override val question: String,
) : Question


enum class QuestionType {
    TRUE_FALSE,
    MULTIPLE_CHOICE,
    MULTIPLE_SELECTION,
    OPEN_ENDED,
}