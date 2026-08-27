package org.example.api

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.google.gson.Gson
import org.example.Model.DataCountry
import org.example.Model.DataCountryByContinent
import org.example.Model.OneDataCountry

class GetCountryService{
    /**This is my class to perform everything regarding country resource
     * This contains 3 functions that will perform the get resources
     */
    //why declare them this way

    //Gson docs: https://javadoc.io/doc/com.google.code.gson/gson/latest/com.google.gson/com/google/gson/Gson.html
    private val gson = Gson()
    // Jackson Docs:
    private val mapper = jacksonObjectMapper()
    val keyValue ="countries"

    // returns a country when you input the country_ID
    fun getACountry(inputId:Int):OneDataCountry?{
        val newKeyValue ="$keyValue/$inputId"

        val response =GetResponseService().getResponse(newKeyValue)
        var country :OneDataCountry? = null
        if(!response.isSuccessful){
            println("Error ${response.code} ${response.message}")

        }else{
            // if it successful then get the body of the response
            val responseBody = response.body?.string()
            if (responseBody != null) {
                // need to explain how I got here
                country=gson.fromJson(responseBody, OneDataCountry::class.java)
            }else{
                println("there is no body in response")
            }
        }
        return country


    }

    //returns all the countries
    fun getCountries(): DataCountry?{
        val response =GetResponseService().getResponse(keyValue)
        var countries: DataCountry? = null

        if(!response.isSuccessful){
            println("Error ${response.code} ${response.message}")
        }else{
            // if it successful then get the body of the response
            val responseBody = response.body?.string()
            if (responseBody != null) {
                // if the body of the response is not empty
                countries = mapper.readValue(responseBody)
            }else{
                println("there is no body in response")
            }
        }

        return countries
    }

    // returns all the countries in the specified continent
    fun getACountryByContinent(inputValue:String): DataCountryByContinent?{
        val newKeyValue ="$keyValue?continent=$inputValue"
        val response =GetResponseService().getResponse(newKeyValue)
        var countries: DataCountryByContinent? = null

        if(!response.isSuccessful){
            println("Error ${response.code} ${response.message}")
        }else{
            // if it successful then get the body of the response
            val responseBody = response.body?.string()
            if (responseBody != null) {
                countries= gson.fromJson(responseBody, DataCountryByContinent::class.java)
            }else{
                println("there is no body in response")
            }
        }
        return countries


    }
}