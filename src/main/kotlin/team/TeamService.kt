package team
import client.ApiClient
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.google.gson.Gson
import exception.ApiResponseException
import exception.ApiUnavailableException
import okhttp3.Response
import org.example.Team.DataTeam
import org.example.Team.OneDataTeam
import java.io.IOException


class TeamService{

    /**This is my class to perform everything regarding country resource
     * This contains 3 functions that will perform the get resources
     */

    private val gson = Gson()
    private val mapper = jacksonObjectMapper()
    private val apiClient = ApiClient()
    val keyValue ="teams"


    fun getATeam(inputId:Int): OneDataTeam? {
        // returns a team when you input the team_ID
        try {

            val newKeyValue = "$keyValue/$inputId"
            val response = apiClient.getResponse(newKeyValue)
            var team: OneDataTeam? = null

            if (!response.isSuccessful) {
                throw ApiResponseException(response.code)

            } else {
                // if it successful then get the body of the response
                val responseBody = response.body?.string()
                if (responseBody != null) {
                    // need to explain how I got here
                    team = gson.fromJson(responseBody, OneDataTeam::class.java)
                } else {
                    println("there is no body in response")
                }
            }
            return team
        }catch (_: IOException){
            throw ApiUnavailableException("Unable to connect to the API")
        }


    }//getATeam

    //returns all the teams
    fun getTeams(): DataTeam?{
        try {

            val response = apiClient.getResponse(keyValue)
            var teams: DataTeam? = null

            if (!response.isSuccessful) {
                throw ApiResponseException(response.code)
            } else {
                // if it successful then get the body of the response
                val responseBody = response.body?.string()
                if (responseBody != null) {
                    // if the body of the response is not empty
                    teams = mapper.readValue(responseBody)
                } else {
                    println("there is no body in response")
                }
            }

            return teams
        }catch (_: IOException){
            throw ApiUnavailableException("Unable to connect to the API")
        }
    }

    // returns all the teams when team name or country_Id is specified
    // if the query is broken it returns all teams
    fun getTeamByCountryIDOrTeamName(countryId:Int?,teamName:String?,): DataTeam?{
        try {

            val newKeyValue: String
            val response: Response
            if (countryId == null && teamName != null) {
                //the user ob=nly provided the team name
                newKeyValue = "$keyValue?teamName=$teamName"
                response = apiClient.getResponse(newKeyValue)
            } else if (countryId != null && teamName == null) {
                //the user only provided the country_ID
                newKeyValue = "$keyValue?countryId=$countryId"
                response = apiClient.getResponse(newKeyValue)

            } else {
                //both inputs are null
                response = apiClient.getResponse(keyValue)
            }


            var teams: DataTeam? = null

            if (!response.isSuccessful) {
                throw ApiResponseException(response.code)
            } else {
                // if it successful then get the body of the response
                val responseBody = response.body?.string()
                if (responseBody != null) {
                    teams = mapper.readValue(responseBody)
                } else {
                    println("there is no body in response")
                }
            }
            return teams
        }catch (_: IOException){
            throw ApiUnavailableException("Unable to connect to the API")
        }


    }
}