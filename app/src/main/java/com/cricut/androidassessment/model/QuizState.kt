package com.cricut.androidassessment.model

data class QuizState(
    val currentPage: Int = 0,
    val questions: List<Question> = emptyList(),
) {
    val lastQuestionIndex: Int = questions.size - 1

    val questionsTotal: Int
        get() = questions.size

    val questionsAnswered: Int
        get() = questions.count { it.isAnswered == true }

    val questionsNotAnswered: Int
        get() = questionsTotal - questionsAnswered
}
