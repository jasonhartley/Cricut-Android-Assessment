package com.cricut.androidassessment.ui.screens

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.cricut.androidassessment.model.MultipleChoiceQuestion
import com.cricut.androidassessment.model.MultipleSelectionQuestion
import com.cricut.androidassessment.model.OpenEndedQuestion
import com.cricut.androidassessment.model.TrueFalseQuestion
import com.cricut.androidassessment.ui.AssessmentViewModel
import com.cricut.androidassessment.ui.composables.MultipleChoiceQuestionItem
import com.cricut.androidassessment.ui.composables.MultipleSelectionQuestionItem
import com.cricut.androidassessment.ui.composables.OpenEndedQuestionItem
import com.cricut.androidassessment.ui.composables.TrueFalseQuestionItem
import com.cricut.androidassessment.ui.theme.AndroidAssessmentTheme

@Composable
fun AssessmentScreen(
    modifier: Modifier = Modifier,
    viewModel: AssessmentViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    val pagerState = rememberPagerState(pageCount = {
        uiState.questions.size
    })

    LaunchedEffect(pagerState) {
        snapshotFlow { pagerState.currentPage }.collect { page ->
            Log.d("Page change", "Page changed to $page")
            viewModel.onPageChange(page)
        }
    }

    HorizontalPager(
        state = pagerState,
        modifier = modifier.fillMaxSize()
            .padding(16.dp)
    ) { page ->
        val question = uiState.questions[page]

        Column(
            modifier = Modifier
                .padding(8.dp)
        ) {
            when (question) {
                is TrueFalseQuestion -> TrueFalseQuestionItem(question)
                is MultipleChoiceQuestion -> MultipleChoiceQuestionItem(question)
                is MultipleSelectionQuestion -> MultipleSelectionQuestionItem(question)
                is OpenEndedQuestion -> OpenEndedQuestionItem(question)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewAssessmentScreen() {
    AndroidAssessmentTheme {
        AssessmentScreen()
    }
}
