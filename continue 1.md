# Sports Data Mock API

## No Ai usage will be allowed for this task

## Running the application

```bash
java -jar SportsMockData-0.0.1-SNAPSHOT.jar
```

This will start the application on port 8080.

## Making Requests
### Authentication
The application uses basic authentication with a default username of `user` and password of `password`.

### Get Countries
```bash
curl --location 'http://localhost:8080/soccer/countries' \
--header 'Authorization: Basic dXNlcjpwYXNzd29yZA=='
```

## API Documentation
Once the application is running (username is `user` and password is `password`):

* The API documentation can be found at [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)
* The OpenApi documentation can be found at [http://localhost:8080/v3/api-docs](http://localhost:8080/v3/api-docs) this can be imported into Postman if required.

## Requirement Overview

You will need to display an ability to

* Integrate with both rest and soap API's.
* Parse the data from the API's and display it in a human-readable format.
* Make use of maven for dependency management, and make use of okHttp as the web service client.
* Make use of libraries like jackson and gson if using java/kotlin.
* Store code on GitHub and provide links to access it.

Some of the API end points are intentionally meant to return data that don't represent standard data structures.

## Requirement 1

This section pertains to the sports mock data Servise.
You need to create a console application that will:

* Provide the user with all the methods they can request data from
* Accept user input for what method they would like to call.
* Invoke the sports mock data Servise, and return the information to the console for the user.
* All this needs to be in a loop so that we don't need to re-run the application each time. A break option should be provided
* Parse the data from the output of the sports mock data Servise, and print it in a human-readable format (Don't just display a json response, separate out each element )

NOTES for using JAVA/Kotlin:

* Make use of maven for dependency management
* Use okHttp as the web service client
* Don't build any request/response as string in inverted commas (Make use of java objects to represent the request and response)
* Libraries like jackson and gson will be useful

General notes:
* Store this code on GitHub and provide us with links to access it. (THE REPO MUST BE PRIVATE)
* Make use of a constant to identify any country as a default country.
* When the user wants to display 'teams', ask the user if it should use the default country, or one that he would like to specify.
* If the user wants to specify a country, you need to prompt the user for the COUNTRY NAME, then search for teams based on that country.
* If the user doesn't want to specify a specific country, use the default country.

## Requirement 2

* Build a java console temperature converter application that that makes use of the methods in the following API
  `https://www.w3schools.com/xml/tempconvert.asmx`

NOTES for using JAVA/Kotlin:

* Make use of maven for dependency management
* Use okHttp as the web service client, if possible (if not, you can use any other library)
* Don't build any request/response as string in inverted commas (Make use of java objects to represent the request and response)
* Libraries like jackson and axis2 will be useful

General notes:

* Store this code on GitHub and provide us links to access it. (THE REPO MUST BE PRIVATE)

If you are confident with the above, you can build a simple web UI for either/both of the above applications.
