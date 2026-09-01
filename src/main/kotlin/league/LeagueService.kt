package league


import client.ApiClient
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.google.gson.Gson
import exception.ApiResponseException
import exception.ApiUnavailableException
import java.io.IOException


class LeagueService{

    //This should br called leagueservice and inside this class we have all the get functions
    /**This is my class to perform everything regarding country resource
     * This contains 3 functions that will perform the get resources
     */
    //why declare them this way
    private val gson = Gson()
    private val mapper = jacksonObjectMapper()
    private val apiClient = ApiClient()
    val keyValue ="leagues"

    // returns a country when you input the league_ID
    fun getALeague(inputId:Int): OneDataLeague?{

        try {

            val newKeyValue = "$keyValue/$inputId"
            val response = apiClient.getResponse(newKeyValue)
            var league: OneDataLeague? = null

            if (!response.isSuccessful) {
                throw ApiResponseException(response.code)

            } else {
                // if it successful then get the body of the response
                val responseBody = response.body?.string()
                if (responseBody != null) {
                    // need to explain how I got here
                    league = gson.fromJson(responseBody, OneDataLeague::class.java)
                } else {
                    println("there is no body in response")
                }
            }
            return league
        }catch (_: IOException){
            throw ApiUnavailableException("Unable to connect to the API")
        }


    }

    //returns all the leagues
    fun getLeagues(): DataLeague?{
        try {

            val response = apiClient.getResponse(keyValue)
            var leagues: DataLeague? = null

            if (!response.isSuccessful) {
                throw ApiResponseException(response.code)
            } else {
                // if it successful then get the body of the response
                val responseBody = response.body?.string()
                if (responseBody != null) {
                    // if the body of the response is not empty
                    leagues = mapper.readValue(responseBody)
                } else {
                    println("there is no body in response")
                }
            }

            return leagues
        }catch (_: IOException){
            throw ApiUnavailableException("Unable to connect to the API")
        }
    }

    // returns all the leagues in the specified country using the country_ID
    fun getLeagueByCountry(inputValue:Int): DataLeague?{
       try {

           val newKeyValue = "$keyValue?country_id=$inputValue"
           val response = apiClient.getResponse(newKeyValue)
           var leagues: DataLeague? = null

           if (!response.isSuccessful) {
               throw ApiResponseException(response.code)
           } else {
               // if it successful then get the body of the response
               val responseBody = response.body?.string()
               if (responseBody != null) {
                   leagues = gson.fromJson(responseBody, DataLeague::class.java)
               } else {
                   println("there is no body in response")
               }
           }
           return leagues
       }catch (_: IOException){
           throw ApiUnavailableException("Unable to connect to the API")
       }


    }
}