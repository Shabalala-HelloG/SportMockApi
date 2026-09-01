package team

import country.Country
import org.example.Team.DataTeam
import org.example.Team.OneDataTeam
import country.DataCountry
import country.CountryService

class TeamFetcher {
    private val teamService= TeamService()
    private val countryService= CountryService()

    fun fetchAllTeams() {
        // Fetches all teams
        val teamDataList: DataTeam? = teamService.getTeams()
        if (teamDataList == null) {
            println("There is no teams")
        } else {
            val teamList: List<Team> = teamDataList.data

            println("There are ${teamList.size} teams")
            println("Enter how many teams you want to see: ")
            val input = readlnOrNull()?.toIntOrNull()

            if (input != null) {
                if ((input > 0) && (input < (teamList.size + 1))) {
                    // if your input is between 1 and 4132
                    for (i in 0..<input) {
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
    }//fetchAllTeams

    fun fetchATeam() {
        // Fetches a Team
        println("Please provide a number for the team ID:")
        val input = readlnOrNull()?.toIntOrNull()

        if (input != null) {
            val team: OneDataTeam? = teamService.getATeam(input)
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

    }//fetchTeam

    fun fetchTeamByCountryIdOrTeamName() {
        //fetches a team using countryID or/and teamName
        println("Please provide a number for the country ID:")
        val countryId : Int? = readlnOrNull()?.toIntOrNull()
        println("Please provide the name of the team")
        val teamName: String? = readlnOrNull()?.takeIf { it.isNotBlank() }
        val teamDataList = teamService.getTeamByCountryIDOrTeamName(teamName = teamName, countryId = countryId)

        if (teamDataList == null) {
            println("There is no teams")
        } else {
            val teamList: List<Team> = teamDataList.data
            if (teamList.isNotEmpty()){
                println("There are ${teamList.size} teams")
                println("Enter how many teams you want to see: ")
                //for safe user input
                //link: https://kotlinlang.org/docs/read-standard-input.html#handle-standard-input-safely:~:text=Handle%20standard%20input%20safely%EF%BB%BF
                val input = readlnOrNull()?.toIntOrNull()

                if (input != null) {
                    if ((input > 0) && (input < (teamList.size + 1))) {
                        // if your input is between 1 and 4132
                        for (i in 0..<input) {
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


    fun fetchTeamsWithCountry() {
        //fetches Teams from a specific country
        val teamDataList: DataTeam? = teamService.getTeams()

        // this service is used to retrieve the countries
        val countryDataList: DataCountry? = countryService.getCountries()

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
            for((name, _, countryID) in countryList){
                if(name.equals(countryFromUser, ignoreCase = true))countryId= countryID
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
                for ((teamId, teamName, countryId1) in teamList) {
                    val result = countryId1 == countryId
                    if (result) {
                        println("$teamId. $teamName from ${countryFromUser.uppercase()}\n")
                        counter++
                    }
                }
                if (counter == 0) {
                    println("There are no teams under $countryFromUser")
                }
            }

        }
    }
}