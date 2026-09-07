package league

import country.Country
import country.CountryService
import country.DataCountry

class LeagueFetcher {
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
        //User my still be able to select the respective League
        println("Please provide the name for your league:")
        val leagueDataList: DataLeague? = leagueService.getLeagues()
        if (leagueDataList != null) {
            val leagueList: List<League> = leagueDataList.data
            when (val possibleLeague= readlnOrNull()) {
                null -> println("Error: input is not a valid league ")
                else -> {
                    var counter =0
                    for ((name, country, leagueID) in leagueList) {
                        if(name.contains(possibleLeague,true)) {
                            println("$leagueID. $name of the country ${(country.name).uppercase()}. ")
                            counter++
                        }
                    }
                     if(counter==0)println("No league with name $possibleLeague found.")
                }
            }
        }else println("NO leagues found")

    }//fetchALeague

    fun fetchLeaguesByCountry() {
        //Fetches leagues by their country
        countryFinder()// this will print the list of all the possible countries

        println("Enter the countryID of the country you want to fetch from the given list:")
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

    fun countryFinder(){
        //this should return a list
        println("Please provide the name for your country:")
        val countryDataList: DataCountry? = CountryService().getCountries()
        if (countryDataList != null) {
            val countryList: List<Country> = countryDataList.data
            when (val possibleCountry= readlnOrNull()) {
                null -> println("Error: input is not a valid country ")
                else -> {
                    for ((name, _, countryID) in countryList) {
                        if(name.contains(possibleCountry,true)) println("$countryID. $name")
                    }
                }
            }
        }else{
            println("NO countries found")
        }
    }//countryFinder


}