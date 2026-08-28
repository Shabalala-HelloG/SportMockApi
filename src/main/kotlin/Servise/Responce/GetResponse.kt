package org.example.api

import okhttp3.Credentials
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response

class GetResponseService{
    /**I read through 'OkHttp in Kotlin: Complete Guide to GET & POST Requests' webpage on how
     * to use the okHttp
     * link:https://blog.deveshrx.com/okhttp-android-kotlin-tutorial
     * Couldn't get the okHttp documentation because the site is down: http://sqaure.github.io/okhttp
     *
     * I also read up on 'Retrofit And OkHttp' it has a more detailed explanation but I used this to see the consistences in codes
     * link: https://medium.com/@erdi.koc/retrofit-and-okhttp-675d34eb7458
     *
     * the 'A Guide to OkHttp' reading helped me see the important parts that I need to build my code
     * link:https://www.baeldung.com/guide-to-okhttp
     *
     * The articles have a lot of info on how to build a request and get a response.
     * I took what I believed was necessary for me.
     * One particular thing they mentioned was that a common mistake people do is make OkHttpClients calls
     * for every network call. This is why I created a class whose job is just to make a request and collect a response
     *
     * Okhttp3 has a bunch of functions but I and without the documentation I only used what I wanted
     *
     * my thought process was
     * 1. Create a way to make a call
     * 2. Sent request to the API
     * 3. Get the response
     * 4. The error handling is done by me with no fancy funcions- the article did a lot of override
     *
     */

    private val client = OkHttpClient.Builder().build()

    //these are my parameter
    private val baseUrl="http://localhost:8080/soccer"

    /**
     * the examples I was looking at where APIs that didn't take credentials.
     * "Android OkHttp with Basic Authentication" showed me how to bypass this
     * link:https://stackoverflow.com/questions/22490057/android-okhttp-with-basic-authentication
     */

    private val userName="user"
    private val password="password"
    private val credential= Credentials.basic(userName,password)

    fun getResponse(keyValue:String): Response {
        // the keyValue is what I pass in to increment my url to access my resources
        val request = Request.Builder()
            .url("$baseUrl/$keyValue")
            .header("Authorization", credential)
            .build()
        val response = client.newCall(request).execute()
        return response
    }
}