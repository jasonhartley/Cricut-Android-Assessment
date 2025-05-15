package com.cricut.androidassessment.ui.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.listSaver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.unit.dp
import com.cricut.androidassessment.model.MultipleChoiceQuestion
import com.cricut.androidassessment.model.MultipleSelectionQuestion
import com.cricut.androidassessment.model.Question
import com.cricut.androidassessment.model.TrueFalseQuestion

@Composable
fun TrueFalseQuestionItem(
    question: TrueFalseQuestion,
    modifier: Modifier = Modifier,
    onAnswerSelected: (Boolean?) -> Unit = {},
) {
    TrueFalseQuestionItem(
        questionText = question.questionText,
        onAnswerSelected = onAnswerSelected,
        modifier = modifier,
    )
}

@Composable
fun TrueFalseQuestionItem(
    questionText: String,
    modifier: Modifier = Modifier,
    onAnswerSelected: (Boolean?) -> Unit = {},
) {
    val choices: List<Boolean?> = listOf(true, false, null)
    var selectedAnswer: Boolean? by rememberSaveable { mutableStateOf(null) }

    Column {
        Text(
            text = questionText,
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier
                .padding(bottom = 8.dp)
        )
        choices.forEach { choice ->
            if (choice == null) return@forEach

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = modifier
                    .fillMaxWidth()
                    .selectable(
                        selected = (choice == selectedAnswer),
                        onClick = {
                            selectedAnswer = choice
                            onAnswerSelected(choice)
                        }
                    )
                    .padding(horizontal = 16.dp),
            ) {
                RadioButton(
                    selected = (choice == selectedAnswer),
                    onClick = {
                        selectedAnswer = choice
                        onAnswerSelected(choice)
                    }
                )
                Text(
                    text = choice.toString().capitalize(Locale.current),
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(start = 16.dp)
                )
            }
        }
    }
}

@Composable
fun MultipleChoiceQuestionItem(
    question: MultipleChoiceQuestion,
    onAnswerSelected: (String) -> Unit = {},
) {
    MultipleChoiceQuestionItem(
        questionText = question.questionText,
        choices = question.choices,
        onAnswerSelected = onAnswerSelected,
    )
}

@Composable
fun MultipleChoiceQuestionItem(
    questionText: String,
    choices: List<String>,
    onAnswerSelected: (String) -> Unit = {},
) {
    var selectedAnswer by rememberSaveable { mutableStateOf("") }

    Column {
        Text(
            text = questionText,
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier
                .padding(bottom = 8.dp)
        )
        choices.forEach { choice ->
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .selectable(
                        selected = (choice == selectedAnswer),
                        onClick = {
                            selectedAnswer = choice
                            onAnswerSelected(choice)
                        }
                    )
                    .padding(horizontal = 16.dp),
            ) {
                RadioButton(
                    selected = (choice == selectedAnswer),
                    onClick = {
                        selectedAnswer = choice
                        onAnswerSelected(choice)
                    }
                )
                Text(
                    text = choice,
                    modifier = Modifier.padding(start = 16.dp)
                )
            }
        }
    }
}

@Composable
fun MultipleSelectionQuestionItem(
    question: MultipleSelectionQuestion,
    onAnswerSelected: (List<String>) -> Unit = {}
) {
    MultipleSelectionQuestionItem(
        questionText = question.questionText,
        choices = question.choices,
        onAnswerSelected = onAnswerSelected,
    )
}

@Composable
fun MultipleSelectionQuestionItem(
    questionText: String,
    choices: List<String>,
    onAnswerSelected: (List<String>) -> Unit = {}
) {
    var selectedAnswers = rememberMutableStateListOf<String>()

    Column {
        Text(
            text = questionText,
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier
                .padding(bottom = 8.dp)
        )
        choices.forEach { choice ->
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            ) {
                Text(
                    text = choice,
                    modifier = Modifier.weight(1f)
                )
                Checkbox(
                    checked = selectedAnswers.contains(choice),
                    onCheckedChange = { isChecked ->
                        if (isChecked) {
                            selectedAnswers.add(choice)
                        } else {
                            selectedAnswers.remove(choice)
                        }
                        onAnswerSelected(selectedAnswers.toList())
                    }
                )
            }
        }
    }
}

@Composable
fun OpenEndedQuestionItem(
    question: Question,
    onAnswerSelected: (String) -> Unit = {},
) {
    OpenEndedQuestionItem(
        questionText = question.questionText,
        onAnswerSelected = onAnswerSelected,
    )
}

@Composable
fun OpenEndedQuestionItem(
    questionText: String,
    onAnswerSelected: (String) -> Unit = {},
) {
    var answer by rememberSaveable { mutableStateOf("") }

    Column {
        Text(
            text = questionText,
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier
                .padding(bottom = 8.dp)
        )
        OutlinedTextField(
            value = answer,
            onValueChange = { newText ->
                answer = newText
                onAnswerSelected(newText)
            },
        )
    }
}

@Composable
fun <T : Any> rememberMutableStateListOf(vararg elements: T): SnapshotStateList<T> =
    rememberSaveable(
        saver = listSaver(
            save = { it.toList() },
            restore = { it.toMutableStateList() },
        )
    ) { elements.toList().toMutableStateList() }
