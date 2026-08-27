package org.example.consol

import kotlin.system.exitProcess
import org.example.callUps.CallUpFunction
class UserConsole {

    fun consolePlay() {
        //I should print out an instruction sort of thing
        while(true) {
            val userInput: String?
            println("1.View Countries\n2.View Teams\n3.View Leagues\n4.Quit")
            println("-------------------")
            userInput = readlnOrNull()
            val api=CallUpFunction()

            val choiceValue: String?

            if (userInput == null) {
                println("Error: Invalid input please try again later")
            } else if (userInput == "1" || userInput.uppercase() == "COUNTRIES" || userInput.uppercase() == "COUNTRY") {

                println("DO COUNTRY STUFF")//yet to define what happens
                println("1.View all countries\n2.View a country\n3.View by continent")

                choiceValue = readlnOrNull()
                if(choiceValue=="1")api.callCountries()else if( choiceValue =="2") api.call_A_Country() else if(choiceValue == "3")api.call_A_Country_Contient() else if(choiceValue== null) println("Error: Invalid input please try again later") else println("Error: Invalid input please try again")
                println("-------------------\n")

            } else if (userInput == "2" || userInput.uppercase() == "TEAMS" || userInput.uppercase() == "TEAM") {

                println("DO TEAMS STUFF")//yet to define what happens
                println("1.View all teams\n2.View a team\n3.View by countryId or teamName\n4.View teams by country name")

                choiceValue= readlnOrNull()
                if(choiceValue=="1")api.callTeams()else if( choiceValue =="2") api.call_A_Team() else if(choiceValue=="3") api.callTeamByCountryIdOrTeamName()else if(choiceValue=="4")api.callTeamsWithCountry()else if(choiceValue== null) println("Error: Invalid input please try again later") else println("Error: Invalid input please try again")
                println("-------------------\n")
            } else if (userInput == "3" || userInput.uppercase() == "LEAGUES" || userInput.uppercase() == "LEAGUE") {

                println("DO LEAGUES STUFF")//yet to define what happens
                println("1.View all leagues\n2.View a league\n3.View league by country")

                choiceValue= readlnOrNull()
                if(choiceValue=="1")api.callLeagues()else if( choiceValue =="2") api.call_A_League() else if(choiceValue=="3") api.callLeaguesByCountry()else if(choiceValue== null) println("Error: Invalid input please try again later") else println("Error: Invalid input please try again")
                println("-------------------\n")

            } else if (userInput == "4" || userInput.uppercase() == "QUIT") {
                exitProcess(0)//does what I need it to do

            }else{
                println("Error: Invalid input please try again")
                println("-------------------\n")//does what I need it to do
            }
        }
    }
}