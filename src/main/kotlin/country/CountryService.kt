package country


import client.ApiClient
import client.ApiResponse
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import exception.ApiResponseException
import exception.ApiUnavailableException
import java.io.IOException

class CountryService {

    private val mapper = jacksonObjectMapper()
    private val apiClient = ApiClient()
    private val keyValue = "countries"


    fun getACountry(inputId: Int): ApiResponse<Country>? {
        // returns a country when you input the country_ID
        try {

            val newKeyValue = "$keyValue/$inputId"

            val response = apiClient.getResponse(newKeyValue)
            var country: ApiResponse<Country>? = null
            if (!response.isSuccessful) {
                throw ApiResponseException(response.code)

            } else {
                val responseBody = response.body?.string()
                if (responseBody != null) {
                    country = mapper.readValue(responseBody) as ApiResponse<Country>
                } else {
                    println("there is no body in response")
                }
            }
            return country
        } catch (_: IOException) {
            throw ApiUnavailableException("Unable to connect to the API")
        }


    }//getACountry


    fun getCountries(): ApiResponse<List<Country>>? {
        //returns all the countries
        try {
            val response = apiClient.getResponse(keyValue)
            var countries: ApiResponse<List<Country>>? = null

            if (!response.isSuccessful) {
                throw ApiResponseException(response.code)
            } else {
                val responseBody = response.body?.string()
                if (responseBody != null) {
                    countries = mapper.readValue(responseBody) as ApiResponse<List<Country>>?
                } else {
                    println("there is no body in response")
                }
            }

            return countries
        } catch (_: IOException) {
            throw ApiUnavailableException("Unable to connect to the API")
        }
    }//getCountries


    fun getACountryByContinent(inputValue: String): ApiResponse<Map<String, Country>>? {
        // returns all the countries in the specified continent
        try {

            val newKeyValue = "$keyValue?continent=$inputValue"
            val response = apiClient.getResponse(newKeyValue)
            var countries: ApiResponse<Map<String, Country>>? = null

            if (!response.isSuccessful) {
                throw ApiResponseException(response.code)
            } else {
                val responseBody = response.body?.string()
                if (responseBody != null) {
                    countries = mapper.readValue(responseBody) as ApiResponse<Map<String, Country>>?
                } else {
                    println("there is no body in response")
                }
            }
            return countries
        } catch (_: IOException) {
            throw ApiUnavailableException("Unable to connect to the API")
        }

    }//getACountryByContinent
}