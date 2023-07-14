package com.nova.kdocgenerator.model


/**
 * Data class to hold the usage for a given user
 *
 * @property completion_tokens  The number of tokens the user has completed
 * @property prompt_tokens  The number of tokens the user has prompted
 * @property total_tokens  The total number of tokens used by the user
 */
data class Usage(
        val completion_tokens: Int,
        val prompt_tokens: Int,
        val total_tokens: Int
)