package league


import client.ApiClient
import client.ApiResponse
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import exception.ApiResponseException
import exception.ApiUnavailableException
import java.io.IOException


class LeagueService {


    private val mapper = jacksonObjectMapper()
    private val apiClient = ApiClient()
    private val keyValue = "leagues"


    fun getALeague(inputId: Int): ApiResponse<League>? {
        // returns a country when you input the league_ID

        try {

            val newKeyValue = "$keyValue/$inputId"
            val response = apiClient.getResponse(newKeyValue)
            var league: ApiResponse<League>? = null

            if (!response.isSuccessful) {
                throw ApiResponseException(response.code)

            } else {
                val responseBody = response.body?.string()
                if (responseBody != null) {
                    league = mapper.readValue(responseBody) as ApiResponse<League>?
                } else {
                    println("there is no body in response")
                }
            }
            return league
        } catch (_: IOException) {
            throw ApiUnavailableException("Unable to connect to the API")
        }


    }//getALeague


    fun getLeagues(): ApiResponse<List<League>>? {
        //returns all the leagues
        try {

            val response = apiClient.getResponse(keyValue)
            var leagues: ApiResponse<List<League>>? = null

            if (!response.isSuccessful) {
                throw ApiResponseException(response.code)
            } else {
                val responseBody = response.body?.string()
                if (responseBody != null) {
                    leagues = mapper.readValue(responseBody) as ApiResponse<List<League>>?
                } else {
                    println("there is no body in response")
                }
            }

            return leagues
        } catch (_: IOException) {
            throw ApiUnavailableException("Unable to connect to the API")
        }
    }//getLeagues


    fun getLeagueByCountry(inputValue: Int): ApiResponse<List<League>>? {
        // returns all the leagues in the specified country using the country_ID
        try {

            val newKeyValue = "$keyValue?country_id=$inputValue"
            val response = apiClient.getResponse(newKeyValue)
            var leagues: ApiResponse<List<League>>? = null

            if (!response.isSuccessful) {
                throw ApiResponseException(response.code)
            } else {
                val responseBody = response.body?.string()
                if (responseBody != null) {
                    leagues = mapper.readValue(responseBody) as ApiResponse<List<League>>?
                } else {
                    println("there is no body in response")
                }
            }
            return leagues
        } catch (_: IOException) {
            throw ApiUnavailableException("Unable to connect to the API")
        }


    }//getLeagueByCountry
}