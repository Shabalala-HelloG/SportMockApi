package org.example.console

import org.example.Controllers.CountryController
import kotlin.system.exitProcess

class CountryConsole {
    private val fetch = CountryController()

    fun countryMenu(){

        while (true){
            println("1.View all countries\n2.View a country\n3.View by continent\n4.back\n5.quit")

            val choiceValue = readlnOrNull()
            when (choiceValue) {
                "1" -> fetch.fetchAllCountries()
                "2" -> fetch.fetchACountry()
                "3" -> fetch.fetchCountriesByContinent()
                "4"->break
                "5"-> exitProcess(0)
                null -> println("Error: Invalid input choice from the provided options")
                else -> println("Error: Invalid input please choice from the provided options")
            }
            println("-------------------\n")
        }

    }


}