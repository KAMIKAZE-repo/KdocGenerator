package com.nova.kdocgenerator.util

object QueryBuilder {
    fun build(codeSnippet: String, model: String = "text-davinci-003"): String {
        //fin code snippet
        val code = codeSnippet.replace("\n", " ")

        // Prepare the request payload
        val body =  "{\"prompt\": \"generate kdoc for this code in form of code snippet: $code\", \"max_tokens\": 100, \"model\": \"$model\"}"
        print(body)
        return body
    }
}