package com.cricut.androidassessment.model

sealed interface Question {
    val questionText: String
}


data class TrueFalseQuestion(
    override val questionText: String,
) : Question


data class MultipleChoiceQuestion(
    override val questionText: String,
    val choices: List<String>,
) : Question


data class MultipleSelectionQuestion(
    override val questionText: String,
    val choices: List<String>,
) : Question


data class OpenEndedQuestion(
    override val questionText: String,
) : Question
