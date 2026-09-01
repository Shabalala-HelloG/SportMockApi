package country

class CountryFetcher {

    private val countryService= CountryService()

    fun fetchAllCountries() {
        // Fetches all the countries
        val countryDataList: DataCountry? = countryService.getCountries()

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
                if ((input > 0) && (input < (countryList.size + 1))) {
                    for (i in 0..<input) {
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
    }// fetchAllCountries

    fun fetchACountry() {
        //Fetches a country
        println("Please provide a number for the country ID:")
        val input = readlnOrNull()?.toIntOrNull()

        if (input != null) {
            val country: OneDataCountry? = countryService.getACountry(input)
            val countryData = country?.data
            if (countryData != null) {
                println("${countryData.countryID}. ${countryData.name} in continent ${countryData.continent}")
            } else {
                println("There is nothing,this means the country ID isn't associate a country  ")
            }
        } else {
            println("You input was incorrect, its not a valid number ")
        }

    }//fetchACountry

    fun fetchCountriesByContinent() {
        //Fetches countries from the specified continent
        println("These are the continents to choose from:")
        println("1.Africa\n2.Asia\n3.Europe\n4.North America\n5.Oceania\n6.South America")
        println("You can either enter the continent name or it's number on the list")
        println("Please provide the name of the continent:")

        val input = readlnOrNull()

        if (input != null) {
            val continentName : String? = checkContinent(input)
            if(continentName !=null){
                val country: DataCountryByContinent? = countryService.getACountryByContinent(continentName)
                val countryData = country?.data
                if (countryData != null) {
                    for((name, continent, countryID) in countryData.values){
                        println("$countryID. $name in continent $continent")
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

    }//fetchCountryByContinent

    private fun checkContinent(input:String): String?{
        // A simple functions to evaluate user input
        var continent: String?

    when {
        input == "1" || input.equals("Africa", ignoreCase = true) -> {
            continent = "Africa"

        }
        input == "2" || input.equals("Asia", ignoreCase = true) -> {
            continent = "Asia"

        }
        input == "3" || input.equals("Europe", ignoreCase = true) -> {
            continent = "Europe"

        }
        input == "4" || input.equals("North America", ignoreCase = true) -> {
            continent = "North America"

        }
        input == "5" || input.equals("Oceania", ignoreCase = true) -> {
            continent = "Oceania"

        }
        input == "6" || input.equals("South America", ignoreCase = true) -> {
            continent = "South America"

        }
        else -> {
            println("Incorrect continent name")
            continent = null
        }
    }

        return continent
    }

}