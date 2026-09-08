package team

import client.ApiClient
import client.ApiResponse
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import exception.ApiResponseException
import exception.ApiUnavailableException
import okhttp3.Response
import java.io.IOException


class TeamService {

    private val mapper = jacksonObjectMapper()
    private val apiClient = ApiClient()
    private val keyValue = "teams"


    fun getATeam(inputId: Int): ApiResponse<Team>? {
        // returns a team when you input the team_ID
        try {

            val newKeyValue = "$keyValue/$inputId"
            val response = apiClient.getResponse(newKeyValue)
            var team: ApiResponse<Team>? = null

            if (!response.isSuccessful) {
                throw ApiResponseException(response.code)

            } else {
                val responseBody = response.body?.string()
                if (responseBody != null) {
                    team = mapper.readValue(responseBody) as ApiResponse<Team>?
                } else {
                    println("there is no body in response")
                }
            }
            return team
        } catch (_: IOException) {
            throw ApiUnavailableException("Unable to connect to the API")
        }


    }//getATeam

    //returns all the teams
    fun getTeams(): ApiResponse<List<Team>>? {
        try {

            val response = apiClient.getResponse(keyValue)
            var teams: ApiResponse<List<Team>>? = null

            if (!response.isSuccessful) {
                throw ApiResponseException(response.code)
            } else {
                val responseBody = response.body?.string()
                if (responseBody != null) {
                    teams = mapper.readValue(responseBody) as ApiResponse<List<Team>>?
                } else {
                    println("there is no body in response")
                }
            }

            return teams
        } catch (_: IOException) {
            throw ApiUnavailableException("Unable to connect to the API")
        }
    }//getTeams


    fun getTeamByCountryOrTeamName(countryId: Int?, teamName: String?): ApiResponse<List<Team>>? {
        /**
         * returns all the teams when teamName or country_Id is specified
         * when either or are not specified , it returns all the teams
         */
        try {

            val newKeyValue: String
            val response: Response
            when {
                countryId == null && teamName != null -> {
                    newKeyValue = "$keyValue?teamName=$teamName"
                    response = apiClient.getResponse(newKeyValue)
                }

                countryId != null && teamName == null -> {
                    newKeyValue = "$keyValue?countryId=$countryId"
                    response = apiClient.getResponse(newKeyValue)

                }

                else -> {
                    response = apiClient.getResponse(keyValue)
                }
            }


            var teams: ApiResponse<List<Team>>? = null

            if (!response.isSuccessful) {
                throw ApiResponseException(response.code)
            } else {
                val responseBody = response.body?.string()
                if (responseBody != null) {
                    teams = mapper.readValue(responseBody) as ApiResponse<List<Team>>?
                } else {
                    println("there is no body in response")
                }
            }
            return teams
        } catch (_: IOException) {
            throw ApiUnavailableException("Unable to connect to the API")
        }


    }//getTeamByCountryOrTeamName
}