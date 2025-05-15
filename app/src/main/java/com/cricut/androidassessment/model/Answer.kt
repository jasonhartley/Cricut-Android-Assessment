package com.cricut.androidassessment.model

// This interface and the classes that implement it are being used to store
// given answers, and not correct answers, like from an answer key.
// However, if we were to fetch the correct answers from a remote server,
// those answers could also use this implementation for comparison.

sealed interface Answer {
    val isAnswered: Boolean
}


data class TrueFalseAnswer(
    val answer: Boolean?,
) : Answer {
    override val isAnswered: Boolean = answer != null

    companion object {
        val DEFAULT = TrueFalseAnswer(null)
    }
}


data class MultipleChoiceAnswer(
    val answer: String,
) : Answer {
    override val isAnswered: Boolean = answer.isNotBlank()

    companion object {
        val DEFAULT = MultipleChoiceAnswer("")
    }
}


data class MultipleSelectionAnswer(
    val answer: List<String>,
) : Answer {
    override val isAnswered: Boolean = answer.count { it.isNotBlank() } > 0

    companion object {
        val DEFAULT = MultipleSelectionAnswer(emptyList())
    }
}


data class OpenEndedAnswer(
    val answer: String,
) : Answer {
    override val isAnswered: Boolean = answer.isNotBlank()

    companion object {
        val DEFAULT = OpenEndedAnswer("")
    }
}
