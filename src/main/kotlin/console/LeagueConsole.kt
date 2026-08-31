package org.example.console

import org.example.Controllers.LeagueController
import kotlin.system.exitProcess

class LeagueConsole {

    private val fetch = LeagueController()

    fun leagueMenu(){

        while(true) {
            println("1.View all leagues\n2.View a league\n3.View league by country\n4.break\n5.quit")

            val choiceValue = readlnOrNull()
            when (choiceValue) {
                "1" -> fetch.fetchAllLeagues()
                "2" -> fetch.fetchALeague()
                "3" -> fetch.fetchLeaguesByCountry()
                "4" -> break
                "5" -> exitProcess(0)
                null -> println(
                    "Error: Invalid input please try again later"
                )
                else -> println("Error: Invalid input please try again")
            }
            println("-------------------\n")
        }
    }
}