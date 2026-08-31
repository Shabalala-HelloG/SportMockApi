package org.example.Controllers

import org.example.Model.League
import org.example.League.DataLeague
import org.example.League.OneDataLeague
import org.example.api.LeagueService

class LeagueController {
    val leagueService=LeagueService()

    fun fetchAllLeagues() {
        //Fetches all the leagues
        val leagueDataList: DataLeague? = leagueService.getLeagues()
        if (leagueDataList == null) {
            println("The is no data")
        } else {
            val leagueList: List<League> = leagueDataList.data
            /**
             * There is an error
             * leagueList.size =930 but there are 952 elements
             * so here I will just display everything
             */

            val input = 930
            for (i in 0..<input) {
                val league = leagueList[i]
                println("${league.leagueID}. ${league.name} League from ${league.country.name}")
            }
        }
    }//fetchAllLeagues

    fun fetchALeague() {
        // Fetches a league
        println("Please provide a number for the league ID:")
        val input = readlnOrNull()?.toIntOrNull()

        if (input != null) {
            val league: OneDataLeague? = leagueService.getALeague(input)
            val leagueData = league?.data
            if (leagueData != null) {
                println("${leagueData.leagueID}. ${leagueData.name} League from ${leagueData.country.name}")
            } else {
                println("There is nothing ")
            }
        } else {
            println("You input was incorrect, its not a valid number ")
        }

    }//fetchALeague

    fun fetchLeaguesByCountry() {
        //Fetches leagues by their country
        println("Please provide a number for the country ID:")

        val input = readlnOrNull()?.toIntOrNull()
        /**
         * The issues with this is that if the league_ID is out of bound the request is still carried out
         */

        if (input != null) {
            val leagueDataList: DataLeague? = leagueService.getLeagueByCountry(input)
            if (leagueDataList == null) {
                println("The is no data")
            } else {
                val leagueList: List<League> = leagueDataList.data

                println("There are ${leagueList.size} leagues")
                println("Enter how many leagues you want to see: ")
                val input2 = readlnOrNull()?.toIntOrNull()

                if (input2 != null) {
                    if ((input2 > 0) && (input2 < (leagueList.size + 1))) {
                        for (i in 0..<input2) {
                            val league = leagueList[i]
                            println("${league.leagueID}. ${league.name} League from ${league.country.name} with countryId:${league.country.countryID}")
                        }
                    } else {
                        println("Remember, there are ${leagueList.size} leagues")
                    }

                } else {
                    println("Your input is not of a valid type")
                }
            }
        }else{
            println("You input was incorrect, its not a valid number ")
        }


    }//fetchLeaguesByCountry
}