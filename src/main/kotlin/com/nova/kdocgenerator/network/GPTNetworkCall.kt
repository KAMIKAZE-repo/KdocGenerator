package com.nova.kdocgenerator.network

import com.nova.kdocgenerator.credentails.Credentials
import com.nova.kdocgenerator.model.GPTResponse
import com.nova.kdocgenerator.util.GsonUtil
import com.nova.kdocgenerator.util.QueryBuilder
import com.nova.kdocgenerator.util.StringUtil
import org.apache.http.client.HttpClient
import org.apache.http.client.methods.HttpPost
import org.apache.http.entity.StringEntity
import org.apache.http.impl.client.HttpClientBuilder
import org.apache.http.util.EntityUtils
import java.io.IOException

object GPTNetworkCall {
    private const val OPENAI_API_URL = "https://api.openai.com/v1/completions"
    private const val OPENAI_API_KEY = Credentials.OPENAI_API_KEY

    fun gptNetworkCall(codeSnippet: String): String {
        // Perform the REST API call to OpenAI
        return try {
            val httpClient: HttpClient = HttpClientBuilder.create().build()
            val request = HttpPost(OPENAI_API_URL)

            // Set the request headers
            request.addHeader("Authorization", "Bearer $OPENAI_API_KEY")
            request.addHeader("Content-Type", "application/json")

            request.entity = StringEntity(QueryBuilder.build(codeSnippet))
            val response = httpClient.execute(request)

            // Retrieve the response body
            val entity = response.entity
            val responseBody = EntityUtils.toString(entity)

            // Extract the answer from the response
            val answer = extractAnswerFromResponse(responseBody, codeSnippet)

            // Process and display the answer
            answer

            // Close the connection
            // httpClient.close()
        } catch (e: Exception) {
            throw e
        }
    }

    private fun extractAnswerFromResponse(responseBody: String, codeSnippet: String): String {
        val completionText = "//Can't generate documentation\n"
        val response = GsonUtil.fromJson(responseBody, GPTResponse::class.java)
        return try {
            StringUtil.buildResponse(response = response.choices[0].text, selectedCode = codeSnippet)
        } catch (e: Exception) {
            throw e
        }
    }
}
