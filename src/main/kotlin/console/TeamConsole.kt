package console

import exception.ApiResponseException
import exception.ApiUnavailableException
import team.TeamFetcher
import kotlin.system.exitProcess

class TeamConsole {

    private val fetch = TeamFetcher()

    fun teamMenu(){

        while(true){

            println("1.View all teams\n2.View a team\n3.View by countryId or teamName\n4.View teams by country name\n5.back\n6.quit")
            try {


                val choiceValue = readlnOrNull()
                when (choiceValue) {
                    "1" -> fetch.fetchAllTeams()
                    "2" -> fetch.fetchATeam()
                    "3" -> fetch.fetchTeamByCountryIdOrTeamName()
                    "4" -> fetch.fetchTeamsWithCountry()
                    "5" -> break
                    "6" -> exitProcess(0)
                    null -> println("Error: Invalid input please try again later")
                    else -> println("Error: Invalid input please try again")
                }
                println("-------------------\n")
            }catch (e: ApiUnavailableException) {
                println(e.message)

            } catch (e: ApiResponseException) {

                when (e.statusCode) {
                    401 -> println("Authentication failed.")
                    404 -> println("The requested resource was not found.")
                    else -> println("The API returned an error: ${e.statusCode}")
                }
            }

        }
    }
}