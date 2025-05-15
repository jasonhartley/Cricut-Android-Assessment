package com.cricut.androidassessment.ui.screens

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.cricut.androidassessment.QuizRepository
import com.cricut.androidassessment.model.*
import com.cricut.androidassessment.ui.AssessmentViewModel
import com.cricut.androidassessment.ui.composables.*
import com.cricut.androidassessment.ui.theme.AndroidAssessmentTheme

@Composable
fun AssessmentScreen(
    modifier: Modifier = Modifier,
    viewModel: AssessmentViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    val pagerState = rememberPagerState(
        pageCount = { uiState.questions.size + 1 },
    )

    LaunchedEffect(pagerState) {
        snapshotFlow { pagerState.currentPage }.collect { page ->
            Log.d("AssessmentScreen", "Page changed to $page")
            viewModel.onPageChange(page)
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        HorizontalPager(
            state = pagerState,
            modifier = modifier.fillMaxSize()
                .padding(16.dp)
        ) { page ->
            Column(
                modifier = Modifier
                    .padding(8.dp)
            ) {
                if (page > uiState.lastQuestionIndex) {
                    SummaryPage(viewModel)
                } else {
                    val question = uiState.questions[page]
                    when (question) {
                        is TrueFalseQuestion -> TrueFalseQuestionItem(
                            question = question,
                            onAnswerSelected = { answerValue ->
                                viewModel.onUpdateAnswer(
                                    index = page,
                                    trueFalseQuestion = question,
                                    givenAnswer = TrueFalseAnswer(answerValue),
                                )
                            }
                        )
                        is MultipleChoiceQuestion -> MultipleChoiceQuestionItem(
                            question = question,
                            onAnswerSelected = { answerValue ->
                                viewModel.onUpdateAnswer(
                                    index = page,
                                    multipleChoiceQuestion = question,
                                    givenAnswer = MultipleChoiceAnswer(answerValue),
                                )
                            }
                        )
                        is MultipleSelectionQuestion -> MultipleSelectionQuestionItem(
                            question = question,
                            onAnswerSelected = { answerValue ->
                                viewModel.onUpdateAnswer(
                                    index = page,
                                    multipleSelectionQuestion = question,
                                    givenAnswer = MultipleSelectionAnswer(answerValue),
                                )
                            }
                        )
                        is OpenEndedQuestion -> OpenEndedQuestionItem(
                            question = question,
                            onAnswerSelected = { answerValue ->
                                viewModel.onUpdateAnswer(
                                    index = page,
                                    openEndedQuestion = question,
                                    givenAnswer = OpenEndedAnswer(answerValue),
                                )
                            }
                        )
                    }
                }
            }
        }
        PageIndicator(pagerState)
    }
}

@Composable
private fun BoxScope.PageIndicator(pagerState: PagerState) {
    Row(
        modifier = Modifier
            .wrapContentHeight()
            .align(Alignment.BottomCenter)
            .fillMaxWidth()
            .padding(bottom = 32.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        repeat(pagerState.pageCount) { iteration ->
            val color = if (pagerState.currentPage == iteration) Color.DarkGray else Color.LightGray
            Box(
                modifier = Modifier
                    .padding(8.dp)
                    .clip(CircleShape)
                    .background(color)
                    .size(16.dp)
            )
        }
    }
}

@SuppressLint("ViewModelConstructorInComposable")
@Preview(showBackground = true)
@Composable
private fun PreviewAssessmentScreen() {
    AndroidAssessmentTheme {
        AssessmentScreen(viewModel = AssessmentViewModel(QuizRepository()))
    }
}
