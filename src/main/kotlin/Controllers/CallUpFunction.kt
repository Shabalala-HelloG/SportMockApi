package org.example.callUps

import org.example.Model.*
import org.example.api.*

/**
 * CallUpFunction class is how I present my data
 *
 */
class CallUpFunction {
    //Delete this class, make  classes for the respective endpoints
    // Change the callFunctions to

    val serviceLeague=GetLeagueService()
    val serviceTeam=GetTeamService()
    val serviceCountry=GetCountryService()


    fun callCountries() {
        // this is how I get the countries, this is my Kotlin object
        val countryDataList: DataCountry? = serviceCountry.getCountries()

        if (countryDataList == null) {
            println("The are no countries")
        } else {
            val countryList: List<Country> = countryDataList.data

            println("There are ${countryList.size} countries")
            println("Enter how many countries you want to see: ")

            //link: https://kotlinlang.org/docs/read-standard-input.html#handle-standard-input-safely:~:text=Handle%20standard%20input%20safely%EF%BB%BF
            val input = readlnOrNull()?.toIntOrNull()

            if (input != null) {
                // this for loop is to control how many data do you want to see rather than printing all of them
                if (input > 0 && input < countryList.size+1) {
                    for (i in 0..input - 1) {
                        val country = countryList[i]
                        println("${country.countryID}. ${country.name} in continent ${country.continent}")
                    }
                } else {
                    println("Remember, there are ${countryList.size} countries")
                }


            } else {
                println("Your input is Null")
            }

        }
    }

    fun callTeams() {
        val teamDataList: DataTeam? = serviceTeam.getTeams()
        if (teamDataList == null) {
            println("There is no teams")
        } else {
            val teamList: List<Team> = teamDataList.data

            println("There are ${teamList.size} teams")
            println("Enter how many teams you want to see: ")
            val input = readlnOrNull()?.toIntOrNull()

            if (input != null) {
                if (input > 0 && input < teamList.size+1) {
                    // if your input is between 1 and 4132
                    for (i in 0..input - 1) {
                        val team = teamList[i]
                        if (team.foundYear != 0) {
                            println("${team.teamId}. ${team.teamName} was founded in ${team.foundYear}")
                        } else {
                            // some teams have no found
                            println("${team.teamId}. ${team.teamName}")
                        }

                    }
                } else {
                    println("Remember, there are ${teamList.size} teams")
                }

            } else {
                println("Your input is not of a valid type ")
            }
        }
    }

    fun callLeagues() {
        val leagueDataList: DataLeague? = serviceLeague.getLeagues()
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

            if (input != null) {
                if (input > 0 && input < leagueList.size+1) {
                    for (i in 0..input - 1) {
                        val league = leagueList[i]
                        println("${league.leagueID}. ${league.name} League from ${league.country.name}")
                    }
                } else {
                    println("Remember, there are ${leagueList.size} leagues")
                }

            } else {
                println("Your input is not of a valid type")
            }
        }
    }

    fun call_A_Country() {
        println("Please provide a number for the country ID:")
        val input = readlnOrNull()?.toIntOrNull()

        if (input != null) {
            val country: OneDataCountry? = serviceCountry.getACountry(input)
            val countryData = country?.data
            if (countryData != null) {
                println("${countryData.countryID}. ${countryData.name} in continent ${countryData.continent}")
            } else {
                println("There is nothing,this means the country ID isn't associate a country  ")
            }
        } else {
            println("You input was incorrect, its not a valid number ")
        }

    }

    fun call_A_League() {
        println("Please provide a number for the league ID:")
        val input = readlnOrNull()?.toIntOrNull()

        if (input != null) {
            val league: OneDataLeague? = serviceLeague.getALeague(input)
            val leagueData = league?.data
            if (leagueData != null) {
                println("${leagueData.leagueID}. ${leagueData.name} League from ${leagueData.country.name}")
            } else {
                println("There is nothing ")
            }
        } else {
            println("You input was incorrect, its not a valid number ")
        }

    }

    fun call_A_Team() {
        // there is an error everytime I
        println("Please provide a number for the team ID:")
        val input = readlnOrNull()?.toIntOrNull()

        if (input != null) {
            val team: OneDataTeam? = serviceTeam.getATeam(input)
            val teamData = team?.data
            if (teamData != null) {
                if (teamData.foundYear != 0) {
                    println("${teamData.teamId}. ${teamData.teamName} was founded in ${teamData.foundYear}")
                } else {
                    println("${teamData.teamId}. ${teamData.teamName}")
                }
            } else {
                println("There is nothing ")
            }
        } else {
            println("You input was incorrect, its not a valid number ")
        }

    }

    fun call_A_Country_Contient() {
        println("These are the contients to choose from:")
        println("1.Africa\n2.Asia\n3.Europe\n4.North America\n5.Oceania\n6.South America")
        println("You can either enter the continent name or it's number on the list")
        println("Please provide the name of the continent:")

        val input = readLine()

        if (input != null) {
            val contientName : String? = checkContinent(input)
            if(contientName !=null){
                val country: DataCountryByContinent? = serviceCountry.getACountryByContinent(contientName)
                val countryData = country?.data
                if (countryData != null) {
                    for(countries in countryData.values){
                        println("${countries.countryID}. ${countries.name} in continent ${countries.continent}")
                    }

                } else {
                    println("There is nothing ")
                }
            }else{
                println("Not a valid continent name")
            }

        } else {
            println("You input was incorrect, its not a valid content ")
        }


    }

    fun callLeaguesByCountry() {
        println("Please provide a number for the country ID:")

        val input = readlnOrNull()?.toIntOrNull()
        /**
         * The issues with this is that if the league_ID is out of bound the request is still carried out
         */

        if (input != null) {
            val leagueDataList: DataLeague? = serviceLeague.getLeagueByCountry(input)
            if (leagueDataList == null) {
                println("The is no data")
            } else {
                val leagueList: List<League> = leagueDataList.data

                println("There are ${leagueList.size} leagues")
                println("Enter how many leagues you want to see: ")
                val input2 = readlnOrNull()?.toIntOrNull()

                if (input2 != null) {
                    if (input2 > 0 && input2 < leagueList.size+1) {
                        for (i in 0..input2- 1) {
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


    }

    fun callTeamByCountryIdOrTeamName() {
        println("Please provide a number for the country ID:")
        val countryId : Int? = readlnOrNull()?.toIntOrNull()
        println("Please provide the name of the team")
        val teamName: String? = readlnOrNull()?.takeIf { it.isNotBlank() }

        val teamDataList : DataTeam?
        teamDataList = serviceTeam.getTeamByCountryIDOrTeamName(teamName = teamName, countryId = countryId)

        if (teamDataList == null) {
            println("There is no teams")
        } else {
            val teamList: List<Team> = teamDataList.data
            if (teamList.size != 0){
                println("There are ${teamList.size} teams")
                println("Enter how many teams you want to see: ")
                //for safe user input
                //link: https://kotlinlang.org/docs/read-standard-input.html#handle-standard-input-safely:~:text=Handle%20standard%20input%20safely%EF%BB%BF
                val input = readlnOrNull()?.toIntOrNull()

                if (input != null) {
                    if (input > 0 && input < teamList.size + 1) {
                        // if your input is between 1 and 4132
                        for (i in 0..input - 1) {
                            val team = teamList[i]
                            if (team.foundYear != 0) {
                                println("${team.teamId}. ${team.teamName} was founded in ${team.foundYear}")
                            } else {
                                // some teams have no found
                                println("${team.teamId}. ${team.teamName}")
                            }

                        }
                    } else {
                        println("Remember, there are ${teamList.size} teams")
                    }

                } else {
                    println("Your input is Null")
                }
            }else{
                println("There are not teams associated with the entered input(s)")
            }

        }
    }
    //calling teams using a specific country
    fun callTeamsWithCountry() {
        //this service is used to retrieve the Teams
        val teamDataList: DataTeam? = serviceTeam.getTeams()

        // this service is used to retrieve the countries
        val countryDataList: DataCountry? = serviceCountry.getCountries()

        println("Please provide the name of the country(not case sensitive)")
        var countryFromUser: String? = readlnOrNull()?.takeIf { it.isNotBlank() }
        val defaultCountry= "south africa"

        if(countryFromUser == null){
            println("Since you did not provide a country we will use default country: South africa")
            countryFromUser=defaultCountry
        }

        var countryId: Int? = null
        val teamList: List<Team>
        val countryList: List<Country>
        //this is just to check if the responseBody is not empty
        if (countryDataList == null) {
            println("There is no countries")
        }else{
            countryList = countryDataList.data
            for(country in countryList){
                //println("country ${country.name} has countryId:${country.countryID}")
                if(country.name.equals(countryFromUser, ignoreCase = true))countryId=country.countryID
            }

        }
        if(countryId==null){
            println("This team does not exist")
        }else {
            println("The country code of $countryFromUser is $countryId")
            var counter = 0
            if (teamDataList == null) {
                println("There is no teams")
            } else {
                teamList = teamDataList.data
                for (team in teamList) {
                    val result = team.countryId == countryId
                    if (result) {
                        println("${team.teamId}. ${team.teamName} from ${countryFromUser.uppercase()}\n")
                        counter++
                    }
                }
                if (counter == 0) {
                    println("There are no teams under $countryFromUser")
                }
            }

        }
    }

    fun checkContinent(input:String): String?{
        var continent: String?=""

        if (input == "1" || input.equals("Africa", ignoreCase = true)) {
            continent = "Africa"

        } else if (input == "2" || input.equals("Asia", ignoreCase = true)) {
            continent = "Asia"

        } else if (input == "3" || input.equals("Europe", ignoreCase = true)) {
            continent = "Europe"

        } else if (input == "4" || input.equals("North America", ignoreCase = true)) {
            continent = "North America"

        } else if (input == "5" || input.equals("Oceania", ignoreCase = true)) {
            continent = "Oceania"

        } else if (input == "6" || input.equals("South America", ignoreCase = true)) {
            continent = "South America"

        } else {
            println("Incorrect continent name")
            continent=null
        }

        return continent
    }

}