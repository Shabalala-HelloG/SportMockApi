package service

import client.ApiClient
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import exception.ApiException
import exception.ApiResponseException
import exception.ApiUnavailableException
import java.io.IOException

class Services {
    val mapper = jacksonObjectMapper()

    inline fun <reified T> services(keyValue:String):T? {
        try {

            val response = ApiClient().getResponse(keyValue)
            if (!response.isSuccessful) throw ApiResponseException(response.code)
            val responseBody = response.body?.string() ?: throw ApiException("There is no content in the response")

            return mapper.readValue(responseBody)
        } catch (_: IOException) {
            throw ApiUnavailableException("Unable to connect to the API")
        }
    }
}