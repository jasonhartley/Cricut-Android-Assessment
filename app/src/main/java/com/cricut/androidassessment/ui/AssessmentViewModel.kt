package com.cricut.androidassessment.ui

import androidx.lifecycle.ViewModel
import com.cricut.androidassessment.QuizRepository
import com.cricut.androidassessment.model.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class AssessmentViewModel
@Inject constructor(
    // It is likely that a more robust repository would need to be made into a Hilt singleton,
    // and not an object, but in its current state, doing plain ole constructor injection is fine.
    quizRepository: QuizRepository,
) : ViewModel() {
    private val _uiState = MutableStateFlow<QuizState>(quizRepository.quiz.value)
    val uiState: StateFlow<QuizState> = _uiState.asStateFlow()

    val questionsTotal: Int
        get() = _uiState.value.questionsTotal

    val questionsAnswered: Int
        get() = _uiState.value.questionsAnswered

    val questionsNotAnswered: Int
        get() = _uiState.value.questionsNotAnswered

    fun onPageChange(page: Int) {
        _uiState.value = _uiState.value.copy(currentPage = page)
    }

    fun onUpdateAnswer(
        index: Int,
        trueFalseQuestion: TrueFalseQuestion,
        givenAnswer: TrueFalseAnswer,
    ) {
        val mutableList = _uiState.value.questions.toMutableList()
        val updatedQuestion = trueFalseQuestion.copy(givenAnswer = givenAnswer)
        mutableList[index] = updatedQuestion
        _uiState.value = _uiState.value.copy(questions = mutableList.toList())
    }

    fun onUpdateAnswer(
        index: Int,
        multipleChoiceQuestion: MultipleChoiceQuestion,
        givenAnswer: MultipleChoiceAnswer,
    ) {
        val mutableList = _uiState.value.questions.toMutableList()
        val updatedQuestion = multipleChoiceQuestion.copy(givenAnswer = givenAnswer)
        mutableList[index] = updatedQuestion
        _uiState.value = _uiState.value.copy(questions = mutableList.toList())
    }

    fun onUpdateAnswer(
        index: Int,
        multipleSelectionQuestion: MultipleSelectionQuestion,
        givenAnswer: MultipleSelectionAnswer,
    ) {
        val mutableList = _uiState.value.questions.toMutableList()
        val updatedQuestion = multipleSelectionQuestion.copy(givenAnswer = givenAnswer)
        mutableList[index] = updatedQuestion
        _uiState.value = _uiState.value.copy(questions = mutableList.toList())
    }

    fun onUpdateAnswer(
        index: Int,
        openEndedQuestion: OpenEndedQuestion,
        givenAnswer: OpenEndedAnswer,
    ) {
        val mutableList = _uiState.value.questions.toMutableList()
        val updatedQuestion = openEndedQuestion.copy(givenAnswer = givenAnswer)
        mutableList[index] = updatedQuestion
        _uiState.value = _uiState.value.copy(questions = mutableList.toList())
    }

}
