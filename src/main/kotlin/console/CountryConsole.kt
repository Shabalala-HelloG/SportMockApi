package console

import country.CountryFetcher
import exception.ApiResponseException
import exception.ApiUnavailableException
import kotlin.system.exitProcess

class CountryConsole {
    private val fetch = CountryFetcher()

    fun countryMenu(){

        while (true) {
            println("1.View all countries\n2.View a country\n3.View by continent\n4.back\n5.quit")

            try {


            val choiceValue = readlnOrNull()
            when (choiceValue) {
                "1" -> fetch.fetchAllCountries()
                "2" -> fetch.fetchACountry()
                "3" -> fetch.fetchCountriesByContinent()
                "4" -> break
                "5" -> exitProcess(0)
                null -> println("Error: Invalid input choice from the provided options")
                else -> println("Error: Invalid input please choice from the provided options")
            }
            println("-------------------\n")
        }catch (e: ApiUnavailableException) {
                println(e.message)

            } catch (e: ApiResponseException) {

                when (e.statusCode) {
                    //for now, I only cater for the following
                    401 -> println("${e.statusCode}:Authentication failed.")
                    404 -> println("${e.statusCode}:The requested resource was not found.")
                    else -> println("The API returned an error: ${e.statusCode}")
                }
            }
        }

    }


}