package com.cricut.androidassessment.ui.composables

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.unit.dp
import com.cricut.androidassessment.R
import com.cricut.androidassessment.ui.AssessmentViewModel

@Composable
fun SummaryPage(
    viewModel: AssessmentViewModel,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        Text(
            text = stringResource(R.string.summary).capitalize(Locale.current),
            style = MaterialTheme.typography.titleLarge,
            modifier = modifier.padding(bottom = 16.dp)
        )
        Text(
            text = "${stringResource(R.string.questions_total).capitalize(Locale.current)}: " +
                "${viewModel.questionsTotal}",
            style = MaterialTheme.typography.bodyLarge,
            modifier = modifier.padding(start = 16.dp, bottom = 16.dp)
        )
        Text(
            text = "${stringResource(R.string.questions_answered).capitalize(Locale.current)}: " +
                "${viewModel.questionsAnswered}",
            style = MaterialTheme.typography.bodyLarge,
            modifier = modifier.padding(start = 16.dp, bottom = 16.dp)
        )
        Text(
            text = "${stringResource(R.string.questions_not_answered).capitalize(Locale.current)}: " +
                "${viewModel.questionsNotAnswered}",
            style = MaterialTheme.typography.bodyLarge,
            modifier = modifier.padding(start = 16.dp, bottom = 16.dp)
        )
        Button(
            enabled = viewModel.questionsAnswered == viewModel.questionsTotal,
            // For now we're not validating the given answers, but this would be the
            // mechanism to do so.
            onClick = {
                Log.d("SummaryPage", "submit button tapped")
            },
            modifier = Modifier.padding(16.dp)
        ) {
            Text(text = stringResource(R.string.submit).capitalize(Locale.current))
        }
    }
}