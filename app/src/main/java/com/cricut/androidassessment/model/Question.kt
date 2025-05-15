package com.cricut.androidassessment.model

sealed interface Question {
    val questionText: String
    val givenAnswer: Answer
    val isAnswered: Boolean
}


// To obviate the potential problem of having a question and answer pair be of disparate types,
// we've made the the Answer type be subordinate to the question type to ensure consistency.
// This kind of tight coupling could cause potential problems as well, but given the size and
// scope of the app in its current state, this is a safer approach.

data class TrueFalseQuestion(
    override val questionText: String,
    override val givenAnswer: Answer = TrueFalseAnswer.DEFAULT,
) : Question {
    override val isAnswered: Boolean
        get() = givenAnswer.isAnswered
}


data class MultipleChoiceQuestion(
    override val questionText: String,
    override val givenAnswer: Answer = MultipleChoiceAnswer.DEFAULT,
    val choices: List<String>,
) : Question {
    override val isAnswered: Boolean
        get() = givenAnswer.isAnswered
}


data class MultipleSelectionQuestion(
    override val questionText: String,
    override val givenAnswer: Answer = MultipleSelectionAnswer.DEFAULT,
    val choices: List<String>,
) : Question {
    override val isAnswered: Boolean
        get() = givenAnswer.isAnswered
}


data class OpenEndedQuestion(
    override val questionText: String,
    override val givenAnswer: Answer = OpenEndedAnswer.DEFAULT,
) : Question {
    override val isAnswered: Boolean
        get() = givenAnswer.isAnswered
}
