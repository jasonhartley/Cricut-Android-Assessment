package com.cricut.androidassessment.model

data class QuizState(
    val currentPage: Int = 0,
    val questions: List<Question> = emptyList(),
)