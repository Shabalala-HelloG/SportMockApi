package org.example.Model

/** Since my data is contained inside an object I am creating an object that
 * will hold my data ie:
 * {
 * data : Country -> this is the data that I actually want
 * }
 */

// These data classed are there to accommodate for array in the returned JSON object
data class DataCountry(
    var data: List<Country>
)
data class DataLeague(
    var data: List<League>
)
data class DataTeam(
    var data: List<Team>
)
// yet tp explain the use of these
data class OneDataCountry(
    var data: Country
)
data class OneDataLeague(
    var data: League
)
data class OneDataTeam(
    var data: Team
)
// Still need to explain this one
data class DataCountryByContinent(
    var data:Map<String,Country>
)