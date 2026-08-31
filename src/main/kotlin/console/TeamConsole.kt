package org.example.console

import org.example.Controllers.TeamController
import kotlin.system.exitProcess

class TeamConsole {

    private val fetch = TeamController()

    fun teamMenu(){

        while(true){

            println("1.View all teams\n2.View a team\n3.View by countryId or teamName\n4.View teams by country name\n5.back\n6.quit")

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

        }
    }
}