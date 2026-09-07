package console

import kotlin.system.exitProcess

class UserConsole {

    fun mainConsolePlay() {
        //I should print out an instruction sort of thing
        while(true) {
            val userInput: String?
            println("1.View Countries\n2.View Teams\n3.View Leagues\n4.Quit")
            println("-------------------")

            userInput = readlnOrNull()

            when {
                userInput == null -> {
                    println("Error: Invalid input please choice from the provided options")
                }
                userInput == "1" || userInput.uppercase() == "COUNTRIES" || userInput.uppercase() == "COUNTRY" -> {

                    println("DO COUNTRY STUFF")//call country console
                    CountryConsole().countryMenu()
                }

                userInput == "2" || userInput.uppercase() == "TEAMS" || userInput.uppercase() == "TEAM" -> {

                    println("DO TEAMS STUFF")//call team console
                    TeamConsole().teamMenu()
                }

                userInput == "3" || userInput.uppercase() == "LEAGUES" || userInput.uppercase() == "LEAGUE" -> {

                    println("DO LEAGUES STUFF")//yet to define what happens
                    LeagueConsole().leagueMenu()

                }
                userInput == "4" || userInput.uppercase() == "QUIT" -> {
                    exitProcess(0)
                }
                else -> {
                    println("Error: Invalid input please choice from the provided options")
                    println("-------------------\n")
                }
            }
        }
    }
}