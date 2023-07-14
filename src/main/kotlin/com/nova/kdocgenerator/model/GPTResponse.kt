package com.nova.kdocgenerator.model


/**
 * data class GPTResponse
 *
 * @property choices List of Choice objects
 * @property created Unix timestamp when this response was created
 * @property id Unique identifier for this response
 * @property model The model used to generate the response
 * @property object String indicating this is a response object
 * @property usage Usage object containing information about your request and any errors or warnings generated
 */
data class GPTResponse(
        val choices: List<Choice>,
        val created: Int,
        val id: String,
        val model: String,
        val `object`: String,
        val usage: Usage
)