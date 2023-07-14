package com.nova.kdocgenerator.util

object StringUtil {


    /**
     * Builds a response string from the given parameters
     * @param response String to start the response
     * @param selectedCode String to append to the response
     * @return concatenation of the [response] and the [selectedCode]
     */
    fun buildResponse(response: String, selectedCode: String): String {
        return extractKdoc(response) + selectedCode
    }

    private fun extractKdoc(response: String): String {
        val endDocIndex = response.lastIndexOf("*/")
        return response.substring(0, endDocIndex + 3)
    }
}