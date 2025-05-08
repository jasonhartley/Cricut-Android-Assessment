package com.cricut.androidassessment.ui

import androidx.lifecycle.ViewModel
import com.cricut.androidassessment.QuizRepository
import com.cricut.androidassessment.model.QuizState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class AssessmentViewModel
@Inject constructor() : ViewModel() {
    private val _uiState = MutableStateFlow<QuizState>(QuizRepository.quiz.value)
    val uiState: StateFlow<QuizState> = _uiState.asStateFlow()

    fun onPageChange(page: Int) {
        _uiState.value = _uiState.value.copy(currentPage = page)
    }

}
