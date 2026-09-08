package league

import client.ApiResponse
import country.Country
import country.CountryService

class LeagueFetcher {
    private val leagueService = LeagueService()


    fun fetchAllLeagues() {
        //Fetches all the leagues
        val leagueDataList: ApiResponse<List<League>>? = leagueService.getLeagues()
        if (leagueDataList == null) {
            println("The is no data")
        } else {
            val leagueList: List<League> = leagueDataList.data

            /**
             * There is an error
             * leagueList.size =930 but there are 952 elements
             * so here I will just display everything
             */

            val input = 930// if input is anything either then 930, Some leagues won't be displayed
            for (i in 0..<input) {
                val league = leagueList[i]
                println("${league.leagueID}. ${league.name} League from ${league.country.name}")
            }
        }
    }//fetchAllLeagues

    fun fetchALeague() {
        leagueFinder()// this will print the list of all the possible leagues

        println("Enter the leagueID of the league you want to view from the given list:")
        val input = readlnOrNull()?.toIntOrNull()
        if (input != null) {
            val league : ApiResponse<League>?= leagueService.getALeague(input)
            val leagueData = league?.data

            if (leagueData != null) {
                println("${leagueData.leagueID}. ${leagueData.name} League from ${leagueData.country.name}")
            }else{
                println("There is no data")
            }

        }else{
            println("You input was incorrect, its not a valid number ")
        }

    }//fetchALeague

    fun fetchLeaguesByCountry() {
        //Fetches leagues by their country
        countryFinder()// this will print the list of all the possible countries

        println("Enter the countryID of the country you want to view from the given list:")
        val input = readlnOrNull()?.toIntOrNull()
        /**
         * The issues with this is that if the league_ID is out of bound the request is still carried out
         */

        if (input != null) {
            val leagueDataList: ApiResponse<List<League>>? = leagueService.getLeagueByCountry(input)
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
        } else {
            println("You input was incorrect, its not a valid number ")
        }


    }//fetchLeaguesByCountry

    private fun countryFinder() {
        //this should return a list
        println("Please provide the name for your country:")
        val countryDataList: ApiResponse<List<Country>>? = CountryService().getCountries()
        if (countryDataList != null) {
            val countryList: List<Country> = countryDataList.data
            when (val possibleCountry = readlnOrNull()) {
                null -> println("Error: input is not a valid country ")
                else -> {
                    var counter =0
                    for ((name, continent, countryID) in countryList) {
                        if(name.contains(possibleCountry,true))println("$countryID. $name from continent $continent")
                        counter++
                    }
                    if(counter==0)println("No country with name $possibleCountry found.")
                }
            }
        } else println("NO countries found")
    }//countryFinder

    private fun leagueFinder() {
        println("Please provide the name for your league:")
        val leagueDataList: ApiResponse<List<League>>? = leagueService.getLeagues()
        if (leagueDataList != null) {
            val leagueList: List<League> = leagueDataList.data
            when (val possibleLeague = readlnOrNull()) {
                null -> println("Error: input is not a valid league ")
                else -> {
                    var counter = 0
                    for ((name, country, leagueID) in leagueList) {
                        if (name.contains(possibleLeague, true)) {
                            println("$leagueID. $name of the country ${(country.name).uppercase()}. ")
                            counter++
                        }
                    }
                    if (counter == 0) println("No league with name $possibleLeague found.")
                }
            }
        } else println("NO leagues found")
    }


}