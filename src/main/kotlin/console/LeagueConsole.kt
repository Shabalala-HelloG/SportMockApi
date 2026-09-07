package console

import exception.ApiResponseException
import exception.ApiUnavailableException
import league.LeagueFetcher
import kotlin.system.exitProcess

class LeagueConsole {

    private val fetch = LeagueFetcher()

    fun leagueMenu(){

        while(true) {
            println("1.View all leagues\n2.View a league(s)\n3.View league by country\n4.break\n5.quit")

            try {


                val choiceValue = readlnOrNull()
                when (choiceValue) {
                    "1" -> fetch.fetchAllLeagues()
                    "2" -> fetch.fetchALeague()
                    "3" -> fetch.fetchLeaguesByCountry()
                    "4" -> break
                    "5" -> exitProcess(0)
                    null -> continue
                    else -> println("Error: Invalid input please choice from the provided options")
                }
                println("-------------------\n")
            }catch (e: ApiUnavailableException) {
                println(e.message)

            } catch (e: ApiResponseException) {

                when (e.statusCode) {
                    401 -> println("${e.statusCode}Authentication failed,Your credentials are invalid.")
                    404 -> println("${e.statusCode}The requested resource was not found.")
                    301 -> println("${e.statusCode}The requested resource has been permanently moved.")
                    else -> println("The API returned an error: ${e.statusCode}")
                }
            }
        }
    }
}