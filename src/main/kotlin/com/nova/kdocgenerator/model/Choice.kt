package com.nova.kdocgenerator.model


/**
 * Data class that represents a choice selection.
 *
 * @property finish_reason A String that defines the end reason of selection.
 * @property index An Integer defining the index of the selection.
 * @property logprobs Any type defining the log probabilities of the selection.
 * @property text A String that defines the text of the selection.
 */
data class Choice(
        val finish_reason: String,
        val index: Int,
        val logprobs: Any,
        val text: String
)