package country


import client.ApiClient
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.google.gson.Gson
import exception.ApiResponseException
import exception.ApiUnavailableException
import java.io.IOException

class CountryService{
    //This should br called country service and inside this class we have all the get functions
    /**This is my class to perform everything regarding country resource
     * This contains 3 functions that will perform the get resources
     */
    //why declare them this way

    //Gson docs: https://javadoc.io/doc/com.google.code.gson/gson/latest/com.google.gson/com/google/gson/Gson.html
    private val gson = Gson()
    // Jackson Docs:
    private val mapper = jacksonObjectMapper()
    private val apiClient = ApiClient()
    val keyValue ="countries"

    // returns a country when you input the country_ID
    fun getACountry(inputId:Int): OneDataCountry?{
        try {

            val newKeyValue = "$keyValue/$inputId"

            val response = apiClient.getResponse(newKeyValue)
            var country: OneDataCountry? = null
            if (!response.isSuccessful) {
                throw ApiResponseException(response.code)

            } else {
                // if it successful then get the body of the response
                val responseBody = response.body?.string()
                if (responseBody != null) {
                    // need to explain how I got here
                    country = gson.fromJson(responseBody, OneDataCountry::class.java)
                } else {
                    println("there is no body in response")
                }
            }
            return country
        }catch (_: IOException){
            throw ApiUnavailableException("Unable to connect to the API")
        }


    }

    //returns all the countries
    fun getCountries(): DataCountry?{
        try {
            val response = apiClient.getResponse(keyValue)
            var countries: DataCountry? = null

            if (!response.isSuccessful) {
                throw ApiResponseException(response.code)
            } else {
                // if it successful then get the body of the response
                val responseBody = response.body?.string()
                if (responseBody != null) {
                    // if the body of the response is not empty
                    countries = mapper.readValue(responseBody)
                } else {
                    println("there is no body in response")
                }
            }

            return countries
        }catch (_: IOException){
            throw ApiUnavailableException("Unable to connect to the API")
        }
    }

    // returns all the countries in the specified continent
    fun getACountryByContinent(inputValue:String): DataCountryByContinent?{
        try {

            val newKeyValue = "$keyValue?continent=$inputValue"
            val response = apiClient.getResponse(newKeyValue)
            var countries: DataCountryByContinent? = null

            if (!response.isSuccessful) {
                throw ApiResponseException(response.code)
            } else {
                // if it successful then get the body of the response
                val responseBody = response.body?.string()
                if (responseBody != null) {
                    val type = DataCountryByContinent::class.java
                    countries = gson.fromJson(responseBody, type)
                } else {
                    println("there is no body in response")
                }
            }
            return countries
        }catch (_: IOException){
            throw ApiUnavailableException("Unable to connect to the API")
        }

    }
}